/**
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * ```
 *      http://www.apache.org/licenses/LICENSE-2.0
 * ```
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.android.healthconnect.controller.permissions.connectedapps.searchapps

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceGroup
import com.android.healthconnect.controller.R
import com.android.healthconnect.controller.permissions.connectedapps.ConnectedAppMetadata
import com.android.healthconnect.controller.permissions.connectedapps.ConnectedAppStatus.ALLOWED
import com.android.healthconnect.controller.permissions.connectedapps.ConnectedAppStatus.DENIED
import com.android.healthconnect.controller.permissions.connectedapps.ConnectedAppStatus.INACTIVE
import com.android.healthconnect.controller.permissions.connectedapps.ConnectedAppsViewModel
import com.google.android.material.appbar.AppBarLayout
import dagger.hilt.android.AndroidEntryPoint

/** Fragment for search apps screen. */
@AndroidEntryPoint(PreferenceFragmentCompat::class)
class SearchAppsFragment : Hilt_SearchAppsFragment() {

    companion object {
        const val ALLOWED_APPS_CATEGORY = "allowed_apps"
        private const val NOT_ALLOWED_APPS = "not_allowed_apps"
        private const val INACTIVE_APPS = "inactive_apps"
        private const val NO_SEARCH_RESULT = "no_search_result"
    }

    private var mSearchView: SearchView? = null
    private val viewModel: ConnectedAppsViewModel by viewModels()

    private val mAllowedAppsCategory: PreferenceGroup? by lazy {
        preferenceScreen.findPreference(ALLOWED_APPS_CATEGORY)
    }

    private val mNotAllowedAppsCategory: PreferenceGroup? by lazy {
        preferenceScreen.findPreference(NOT_ALLOWED_APPS)
    }

    private val mInactiveAppsPreference: PreferenceGroup? by lazy {
        preferenceScreen.findPreference(INACTIVE_APPS)
    }

    private val mNoSearchResultCategory: PreferenceGroup? by lazy {
        preferenceScreen.findPreference(NO_SEARCH_RESULT)
    }

    private val menuProvider =
        object : MenuProvider, SearchView.OnQueryTextListener {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_apps, menu)
                val searchMenuItem = menu.findItem(R.id.menu_search_apps)
                searchMenuItem.expandActionView()
                searchMenuItem.setOnActionExpandListener(
                    object : MenuItem.OnActionExpandListener {

                        override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                            return true
                        }

                        override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                            showTitleFromCollapsingToolbarLayout()
                            findNavController().popBackStack()
                            return true
                        }
                    })
                mSearchView = searchMenuItem.actionView as SearchView
                mSearchView!!.queryHint = getText(R.string.search_connected_apps)
                mSearchView!!.setOnQueryTextListener(this)
                mSearchView!!.maxWidth = Int.MAX_VALUE
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchConnectedApps(newText)
                return true
            }
        }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.search_apps_screen, rootKey)
        preferenceScreen.addPreference(NoSearchResultPreference(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMenu()
        hideTitleFromCollapsingToolbarLayout()
        viewModel.connectedApps.observe(viewLifecycleOwner) { connectedApps ->
            if (connectedApps.isEmpty()) {
                preferenceScreen
                    .findPreference<Preference>("no_search_result_preference")
                    ?.isVisible = true
            } else {
                preferenceScreen
                    .findPreference<Preference>("no_search_result_preference")
                    ?.isVisible = false
            }

            val connectedAppsGroup = connectedApps.groupBy { it.status }
            updateAllowedApps(connectedAppsGroup[ALLOWED].orEmpty())
            updateDeniedApps(connectedAppsGroup[DENIED].orEmpty())
            updateInactiveApps(connectedAppsGroup[INACTIVE].orEmpty())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        showTitleFromCollapsingToolbarLayout()
    }

    private fun updateInactiveApps(appsList: List<ConnectedAppMetadata>) {
        mInactiveAppsPreference?.removeAll()
        if (appsList.isEmpty()) {
            preferenceScreen.removePreference(mInactiveAppsPreference)
        } else {
            preferenceScreen.addPreference(mInactiveAppsPreference)
            appsList.forEach { app ->
                mInactiveAppsPreference?.addPreference(getAppPreference(app))
            }
        }
    }

    private fun updateAllowedApps(appsList: List<ConnectedAppMetadata>) {
        mAllowedAppsCategory?.removeAll()
        if (appsList.isEmpty()) {
            preferenceScreen.removePreference(mAllowedAppsCategory)
        } else {
            preferenceScreen.addPreference(mAllowedAppsCategory)
            appsList.forEach { app ->
                mAllowedAppsCategory?.addPreference(
                    getAppPreference(app) { navigateToAppInfoScreen(app) })
            }
        }
    }

    private fun updateDeniedApps(appsList: List<ConnectedAppMetadata>) {
        mNotAllowedAppsCategory?.removeAll()
        if (appsList.isEmpty()) {
            preferenceScreen.removePreference(mNotAllowedAppsCategory)
        } else {
            preferenceScreen.addPreference(mNotAllowedAppsCategory)
            appsList.forEach { app ->
                mNotAllowedAppsCategory?.addPreference(
                    getAppPreference(app) { navigateToAppInfoScreen(app) })
            }
        }
    }

    private fun navigateToAppInfoScreen(app: ConnectedAppMetadata) {
        findNavController()
            .navigate(
                R.id.action_searchApps_to_connectedApp,
                bundleOf("packageName" to app.appMetadata.packageName))
    }

    private fun getAppPreference(
        app: ConnectedAppMetadata,
        onClick: (() -> Unit)? = null
    ): Preference {
        return Preference(requireContext()).also {
            it.title = app.appMetadata.appName
            it.icon = app.appMetadata.icon
            it.setOnPreferenceClickListener {
                onClick?.invoke()
                true
            }
        }
    }

    private fun setupMenu() {
        (activity as MenuHost).addMenuProvider(
            menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun hideTitleFromCollapsingToolbarLayout() {
        activity?.findViewById<AppBarLayout>(R.id.app_bar)?.setExpanded(false)
    }

    private fun showTitleFromCollapsingToolbarLayout() {
        activity?.findViewById<AppBarLayout>(R.id.app_bar)?.setExpanded(true)
    }
}