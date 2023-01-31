/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.health.connect.internal.datatypes;

import android.annotation.NonNull;
import android.health.connect.datatypes.Identifier;
import android.health.connect.datatypes.NutritionRecord;
import android.health.connect.datatypes.RecordTypeIdentifier;
import android.os.Parcel;

/**
 * @hide
 * @see NutritionRecord
 */
@Identifier(recordIdentifier = RecordTypeIdentifier.RECORD_TYPE_NUTRITION)
public final class NutritionRecordInternal extends IntervalRecordInternal<NutritionRecord> {
    private double mUnsaturatedFat;
    private double mPotassium;
    private double mThiamin;
    private int mMealType;
    private double mTransFat;
    private double mManganese;
    private double mEnergyFromFat;
    private double mCaffeine;
    private double mDietaryFiber;
    private double mSelenium;
    private double mVitaminB6;
    private double mProtein;
    private double mChloride;
    private double mCholesterol;
    private double mCopper;
    private double mIodine;
    private double mVitaminB12;
    private double mZinc;
    private double mRiboflavin;
    private double mEnergy;
    private double mMolybdenum;
    private double mPhosphorus;
    private double mChromium;
    private double mTotalFat;
    private double mCalcium;
    private double mVitaminC;
    private double mVitaminE;
    private double mBiotin;
    private double mVitaminD;
    private double mNiacin;
    private double mMagnesium;
    private double mTotalCarbohydrate;
    private double mVitaminK;
    private double mPolyunsaturatedFat;
    private double mSaturatedFat;
    private double mSodium;
    private double mFolate;
    private double mMonounsaturatedFat;
    private double mPantothenicAcid;
    private String mMealName;
    private double mIron;
    private double mVitaminA;
    private double mFolicAcid;
    private double mSugar;

    public double getUnsaturatedFat() {
        return mUnsaturatedFat;
    }

    /** returns this object with the specified unsaturatedFat */
    @NonNull
    public NutritionRecordInternal setUnsaturatedFat(double unsaturatedFat) {
        this.mUnsaturatedFat = unsaturatedFat;
        return this;
    }

    public double getPotassium() {
        return mPotassium;
    }

    /** returns this object with the specified potassium */
    @NonNull
    public NutritionRecordInternal setPotassium(double potassium) {
        this.mPotassium = potassium;
        return this;
    }

    public double getThiamin() {
        return mThiamin;
    }

    /** returns this object with the specified thiamin */
    @NonNull
    public NutritionRecordInternal setThiamin(double thiamin) {
        this.mThiamin = thiamin;
        return this;
    }

    public int getMealType() {
        return mMealType;
    }

    /** returns this object with the specified mealType */
    @NonNull
    public NutritionRecordInternal setMealType(int mealType) {
        this.mMealType = mealType;
        return this;
    }

    public double getTransFat() {
        return mTransFat;
    }

    /** returns this object with the specified transFat */
    @NonNull
    public NutritionRecordInternal setTransFat(double transFat) {
        this.mTransFat = transFat;
        return this;
    }

    public double getManganese() {
        return mManganese;
    }

    /** returns this object with the specified manganese */
    @NonNull
    public NutritionRecordInternal setManganese(double manganese) {
        this.mManganese = manganese;
        return this;
    }

    public double getEnergyFromFat() {
        return mEnergyFromFat;
    }

    /** returns this object with the specified energyFromFat */
    @NonNull
    public NutritionRecordInternal setEnergyFromFat(double energyFromFat) {
        this.mEnergyFromFat = energyFromFat;
        return this;
    }

    public double getCaffeine() {
        return mCaffeine;
    }

    /** returns this object with the specified caffeine */
    @NonNull
    public NutritionRecordInternal setCaffeine(double caffeine) {
        this.mCaffeine = caffeine;
        return this;
    }

    public double getDietaryFiber() {
        return mDietaryFiber;
    }

    /** returns this object with the specified dietaryFiber */
    @NonNull
    public NutritionRecordInternal setDietaryFiber(double dietaryFiber) {
        this.mDietaryFiber = dietaryFiber;
        return this;
    }

    public double getSelenium() {
        return mSelenium;
    }

    /** returns this object with the specified selenium */
    @NonNull
    public NutritionRecordInternal setSelenium(double selenium) {
        this.mSelenium = selenium;
        return this;
    }

    public double getVitaminB6() {
        return mVitaminB6;
    }

    /** returns this object with the specified vitaminB6 */
    @NonNull
    public NutritionRecordInternal setVitaminB6(double vitaminB6) {
        this.mVitaminB6 = vitaminB6;
        return this;
    }

    public double getProtein() {
        return mProtein;
    }

    /** returns this object with the specified protein */
    @NonNull
    public NutritionRecordInternal setProtein(double protein) {
        this.mProtein = protein;
        return this;
    }

    public double getChloride() {
        return mChloride;
    }

    /** returns this object with the specified chloride */
    @NonNull
    public NutritionRecordInternal setChloride(double chloride) {
        this.mChloride = chloride;
        return this;
    }

    public double getCholesterol() {
        return mCholesterol;
    }

    /** returns this object with the specified cholesterol */
    @NonNull
    public NutritionRecordInternal setCholesterol(double cholesterol) {
        this.mCholesterol = cholesterol;
        return this;
    }

    public double getCopper() {
        return mCopper;
    }

    /** returns this object with the specified copper */
    @NonNull
    public NutritionRecordInternal setCopper(double copper) {
        this.mCopper = copper;
        return this;
    }

    public double getIodine() {
        return mIodine;
    }

    /** returns this object with the specified iodine */
    @NonNull
    public NutritionRecordInternal setIodine(double iodine) {
        this.mIodine = iodine;
        return this;
    }

    public double getVitaminB12() {
        return mVitaminB12;
    }

    /** returns this object with the specified vitaminB12 */
    @NonNull
    public NutritionRecordInternal setVitaminB12(double vitaminB12) {
        this.mVitaminB12 = vitaminB12;
        return this;
    }

    public double getZinc() {
        return mZinc;
    }

    /** returns this object with the specified zinc */
    @NonNull
    public NutritionRecordInternal setZinc(double zinc) {
        this.mZinc = zinc;
        return this;
    }

    public double getRiboflavin() {
        return mRiboflavin;
    }

    /** returns this object with the specified riboflavin */
    @NonNull
    public NutritionRecordInternal setRiboflavin(double riboflavin) {
        this.mRiboflavin = riboflavin;
        return this;
    }

    public double getEnergy() {
        return mEnergy;
    }

    /** returns this object with the specified energy */
    @NonNull
    public NutritionRecordInternal setEnergy(double energy) {
        this.mEnergy = energy;
        return this;
    }

    public double getMolybdenum() {
        return mMolybdenum;
    }

    /** returns this object with the specified molybdenum */
    @NonNull
    public NutritionRecordInternal setMolybdenum(double molybdenum) {
        this.mMolybdenum = molybdenum;
        return this;
    }

    public double getPhosphorus() {
        return mPhosphorus;
    }

    /** returns this object with the specified phosphorus */
    @NonNull
    public NutritionRecordInternal setPhosphorus(double phosphorus) {
        this.mPhosphorus = phosphorus;
        return this;
    }

    public double getChromium() {
        return mChromium;
    }

    /** returns this object with the specified chromium */
    @NonNull
    public NutritionRecordInternal setChromium(double chromium) {
        this.mChromium = chromium;
        return this;
    }

    public double getTotalFat() {
        return mTotalFat;
    }

    /** returns this object with the specified totalFat */
    @NonNull
    public NutritionRecordInternal setTotalFat(double totalFat) {
        this.mTotalFat = totalFat;
        return this;
    }

    public double getCalcium() {
        return mCalcium;
    }

    /** returns this object with the specified calcium */
    @NonNull
    public NutritionRecordInternal setCalcium(double calcium) {
        this.mCalcium = calcium;
        return this;
    }

    public double getVitaminC() {
        return mVitaminC;
    }

    /** returns this object with the specified vitaminC */
    @NonNull
    public NutritionRecordInternal setVitaminC(double vitaminC) {
        this.mVitaminC = vitaminC;
        return this;
    }

    public double getVitaminE() {
        return mVitaminE;
    }

    /** returns this object with the specified vitaminE */
    @NonNull
    public NutritionRecordInternal setVitaminE(double vitaminE) {
        this.mVitaminE = vitaminE;
        return this;
    }

    public double getBiotin() {
        return mBiotin;
    }

    /** returns this object with the specified biotin */
    @NonNull
    public NutritionRecordInternal setBiotin(double biotin) {
        this.mBiotin = biotin;
        return this;
    }

    public double getVitaminD() {
        return mVitaminD;
    }

    /** returns this object with the specified vitaminD */
    @NonNull
    public NutritionRecordInternal setVitaminD(double vitaminD) {
        this.mVitaminD = vitaminD;
        return this;
    }

    public double getNiacin() {
        return mNiacin;
    }

    /** returns this object with the specified niacin */
    @NonNull
    public NutritionRecordInternal setNiacin(double niacin) {
        this.mNiacin = niacin;
        return this;
    }

    public double getMagnesium() {
        return mMagnesium;
    }

    /** returns this object with the specified magnesium */
    @NonNull
    public NutritionRecordInternal setMagnesium(double magnesium) {
        this.mMagnesium = magnesium;
        return this;
    }

    public double getTotalCarbohydrate() {
        return mTotalCarbohydrate;
    }

    /** returns this object with the specified totalCarbohydrate */
    @NonNull
    public NutritionRecordInternal setTotalCarbohydrate(double totalCarbohydrate) {
        this.mTotalCarbohydrate = totalCarbohydrate;
        return this;
    }

    public double getVitaminK() {
        return mVitaminK;
    }

    /** returns this object with the specified vitaminK */
    @NonNull
    public NutritionRecordInternal setVitaminK(double vitaminK) {
        this.mVitaminK = vitaminK;
        return this;
    }

    public double getPolyunsaturatedFat() {
        return mPolyunsaturatedFat;
    }

    /** returns this object with the specified polyunsaturatedFat */
    @NonNull
    public NutritionRecordInternal setPolyunsaturatedFat(double polyunsaturatedFat) {
        this.mPolyunsaturatedFat = polyunsaturatedFat;
        return this;
    }

    public double getSaturatedFat() {
        return mSaturatedFat;
    }

    /** returns this object with the specified saturatedFat */
    @NonNull
    public NutritionRecordInternal setSaturatedFat(double saturatedFat) {
        this.mSaturatedFat = saturatedFat;
        return this;
    }

    public double getSodium() {
        return mSodium;
    }

    /** returns this object with the specified sodium */
    @NonNull
    public NutritionRecordInternal setSodium(double sodium) {
        this.mSodium = sodium;
        return this;
    }

    public double getFolate() {
        return mFolate;
    }

    /** returns this object with the specified folate */
    @NonNull
    public NutritionRecordInternal setFolate(double folate) {
        this.mFolate = folate;
        return this;
    }

    public double getMonounsaturatedFat() {
        return mMonounsaturatedFat;
    }

    /** returns this object with the specified monounsaturatedFat */
    @NonNull
    public NutritionRecordInternal setMonounsaturatedFat(double monounsaturatedFat) {
        this.mMonounsaturatedFat = monounsaturatedFat;
        return this;
    }

    public double getPantothenicAcid() {
        return mPantothenicAcid;
    }

    /** returns this object with the specified pantothenicAcid */
    @NonNull
    public NutritionRecordInternal setPantothenicAcid(double pantothenicAcid) {
        this.mPantothenicAcid = pantothenicAcid;
        return this;
    }

    public String getMealName() {
        return mMealName;
    }

    /** returns this object with the specified name */
    @NonNull
    public NutritionRecordInternal setMealName(String mealName) {
        this.mMealName = mealName;
        return this;
    }

    public double getIron() {
        return mIron;
    }

    /** returns this object with the specified iron */
    @NonNull
    public NutritionRecordInternal setIron(double iron) {
        this.mIron = iron;
        return this;
    }

    public double getVitaminA() {
        return mVitaminA;
    }

    /** returns this object with the specified vitaminA */
    @NonNull
    public NutritionRecordInternal setVitaminA(double vitaminA) {
        this.mVitaminA = vitaminA;
        return this;
    }

    public double getFolicAcid() {
        return mFolicAcid;
    }

    /** returns this object with the specified folicAcid */
    @NonNull
    public NutritionRecordInternal setFolicAcid(double folicAcid) {
        this.mFolicAcid = folicAcid;
        return this;
    }

    public double getSugar() {
        return mSugar;
    }

    /** returns this object with the specified sugar */
    @NonNull
    public NutritionRecordInternal setSugar(double sugar) {
        this.mSugar = sugar;
        return this;
    }

    @NonNull
    @Override
    public NutritionRecord toExternalRecord() {
        return new NutritionRecord.Builder(buildMetaData(), getStartTime(), getEndTime())
                .setStartZoneOffset(getStartZoneOffset())
                .setEndZoneOffset(getEndZoneOffset())
                .build();
    }

    @Override
    void populateIntervalRecordFrom(@NonNull Parcel parcel) {
        mUnsaturatedFat = parcel.readDouble();
        mPotassium = parcel.readDouble();
        mThiamin = parcel.readDouble();
        mMealType = parcel.readInt();
        mTransFat = parcel.readDouble();
        mManganese = parcel.readDouble();
        mEnergyFromFat = parcel.readDouble();
        mCaffeine = parcel.readDouble();
        mDietaryFiber = parcel.readDouble();
        mSelenium = parcel.readDouble();
        mVitaminB6 = parcel.readDouble();
        mProtein = parcel.readDouble();
        mChloride = parcel.readDouble();
        mCholesterol = parcel.readDouble();
        mCopper = parcel.readDouble();
        mIodine = parcel.readDouble();
        mVitaminB12 = parcel.readDouble();
        mZinc = parcel.readDouble();
        mRiboflavin = parcel.readDouble();
        mEnergy = parcel.readDouble();
        mMolybdenum = parcel.readDouble();
        mPhosphorus = parcel.readDouble();
        mChromium = parcel.readDouble();
        mTotalFat = parcel.readDouble();
        mCalcium = parcel.readDouble();
        mVitaminC = parcel.readDouble();
        mVitaminE = parcel.readDouble();
        mBiotin = parcel.readDouble();
        mVitaminD = parcel.readDouble();
        mNiacin = parcel.readDouble();
        mMagnesium = parcel.readDouble();
        mTotalCarbohydrate = parcel.readDouble();
        mVitaminK = parcel.readDouble();
        mPolyunsaturatedFat = parcel.readDouble();
        mSaturatedFat = parcel.readDouble();
        mSodium = parcel.readDouble();
        mFolate = parcel.readDouble();
        mMonounsaturatedFat = parcel.readDouble();
        mPantothenicAcid = parcel.readDouble();
        mMealName = parcel.readString();
        mIron = parcel.readDouble();
        mVitaminA = parcel.readDouble();
        mFolicAcid = parcel.readDouble();
        mSugar = parcel.readDouble();
    }

    @Override
    void populateIntervalRecordFrom(@NonNull NutritionRecord nutritionRecord) {
        if (nutritionRecord.getUnsaturatedFat() != null) {
            mUnsaturatedFat = nutritionRecord.getUnsaturatedFat().getInKilograms();
        }
        if (nutritionRecord.getPotassium() != null) {
            mPotassium = nutritionRecord.getPotassium().getInKilograms();
        }
        if (nutritionRecord.getThiamin() != null) {
            mThiamin = nutritionRecord.getThiamin().getInKilograms();
        }
        mMealType = nutritionRecord.getMealType();
        if (nutritionRecord.getTransFat() != null) {
            mTransFat = nutritionRecord.getTransFat().getInKilograms();
        }
        if (nutritionRecord.getManganese() != null) {
            mManganese = nutritionRecord.getManganese().getInKilograms();
        }
        if (nutritionRecord.getEnergyFromFat() != null) {
            mEnergyFromFat = nutritionRecord.getEnergyFromFat().getInJoules();
        }
        if (nutritionRecord.getCaffeine() != null) {
            mCaffeine = nutritionRecord.getCaffeine().getInKilograms();
        }
        if (nutritionRecord.getDietaryFiber() != null) {
            mDietaryFiber = nutritionRecord.getDietaryFiber().getInKilograms();
        }
        if (nutritionRecord.getSelenium() != null) {
            mSelenium = nutritionRecord.getSelenium().getInKilograms();
        }
        if (nutritionRecord.getVitaminB6() != null) {
            mVitaminB6 = nutritionRecord.getVitaminB6().getInKilograms();
        }
        if (nutritionRecord.getProtein() != null) {
            mProtein = nutritionRecord.getProtein().getInKilograms();
        }
        if (nutritionRecord.getChloride() != null) {
            mChloride = nutritionRecord.getChloride().getInKilograms();
        }
        if (nutritionRecord.getCholesterol() != null) {
            mCholesterol = nutritionRecord.getCholesterol().getInKilograms();
        }
        if (nutritionRecord.getCopper() != null) {
            mCopper = nutritionRecord.getCopper().getInKilograms();
        }
        if (nutritionRecord.getIodine() != null) {
            mIodine = nutritionRecord.getIodine().getInKilograms();
        }
        if (nutritionRecord.getVitaminB12() != null) {
            mVitaminB12 = nutritionRecord.getVitaminB12().getInKilograms();
        }
        if (nutritionRecord.getZinc() != null) {
            mZinc = nutritionRecord.getZinc().getInKilograms();
        }
        if (nutritionRecord.getRiboflavin() != null) {
            mRiboflavin = nutritionRecord.getRiboflavin().getInKilograms();
        }
        if (nutritionRecord.getEnergy() != null) {
            mEnergy = nutritionRecord.getEnergy().getInJoules();
        }
        if (nutritionRecord.getMolybdenum() != null) {
            mMolybdenum = nutritionRecord.getMolybdenum().getInKilograms();
        }
        if (nutritionRecord.getPhosphorus() != null) {
            mPhosphorus = nutritionRecord.getPhosphorus().getInKilograms();
        }
        if (nutritionRecord.getChromium() != null) {
            mChromium = nutritionRecord.getChromium().getInKilograms();
        }
        if (nutritionRecord.getTotalFat() != null) {
            mTotalFat = nutritionRecord.getTotalFat().getInKilograms();
        }
        if (nutritionRecord.getCalcium() != null) {
            mCalcium = nutritionRecord.getCalcium().getInKilograms();
        }
        if (nutritionRecord.getVitaminC() != null) {
            mVitaminC = nutritionRecord.getVitaminC().getInKilograms();
        }
        if (nutritionRecord.getVitaminE() != null) {
            mVitaminE = nutritionRecord.getVitaminE().getInKilograms();
        }
        if (nutritionRecord.getBiotin() != null) {
            mBiotin = nutritionRecord.getBiotin().getInKilograms();
        }
        if (nutritionRecord.getVitaminD() != null) {
            mVitaminD = nutritionRecord.getVitaminD().getInKilograms();
        }
        if (nutritionRecord.getNiacin() != null) {
            mNiacin = nutritionRecord.getNiacin().getInKilograms();
        }
        if (nutritionRecord.getMagnesium() != null) {
            mMagnesium = nutritionRecord.getMagnesium().getInKilograms();
        }
        if (nutritionRecord.getTotalCarbohydrate() != null) {
            mTotalCarbohydrate = nutritionRecord.getTotalCarbohydrate().getInKilograms();
        }
        if (nutritionRecord.getVitaminK() != null) {
            mVitaminK = nutritionRecord.getVitaminK().getInKilograms();
        }
        if (nutritionRecord.getPolyunsaturatedFat() != null) {
            mPolyunsaturatedFat = nutritionRecord.getPolyunsaturatedFat().getInKilograms();
        }
        if (nutritionRecord.getSaturatedFat() != null) {
            mSaturatedFat = nutritionRecord.getSaturatedFat().getInKilograms();
        }
        if (nutritionRecord.getSodium() != null) {
            mSodium = nutritionRecord.getSodium().getInKilograms();
        }
        if (nutritionRecord.getFolate() != null) {
            mFolate = nutritionRecord.getFolate().getInKilograms();
        }
        if (nutritionRecord.getMonounsaturatedFat() != null) {
            mMonounsaturatedFat = nutritionRecord.getMonounsaturatedFat().getInKilograms();
        }
        if (nutritionRecord.getPantothenicAcid() != null) {
            mPantothenicAcid = nutritionRecord.getPantothenicAcid().getInKilograms();
        }
        mMealName = nutritionRecord.getMealName();
        if (nutritionRecord.getIron() != null) {
            mIron = nutritionRecord.getIron().getInKilograms();
        }
        if (nutritionRecord.getVitaminA() != null) {
            mVitaminA = nutritionRecord.getVitaminA().getInKilograms();
        }
        if (nutritionRecord.getFolicAcid() != null) {
            mFolicAcid = nutritionRecord.getFolicAcid().getInKilograms();
        }
        if (nutritionRecord.getSugar() != null) {
            mSugar = nutritionRecord.getSugar().getInKilograms();
        }
    }

    @Override
    void populateIntervalRecordTo(@NonNull Parcel parcel) {
        parcel.writeDouble(mUnsaturatedFat);
        parcel.writeDouble(mPotassium);
        parcel.writeDouble(mThiamin);
        parcel.writeInt(mMealType);
        parcel.writeDouble(mTransFat);
        parcel.writeDouble(mManganese);
        parcel.writeDouble(mEnergyFromFat);
        parcel.writeDouble(mCaffeine);
        parcel.writeDouble(mDietaryFiber);
        parcel.writeDouble(mSelenium);
        parcel.writeDouble(mVitaminB6);
        parcel.writeDouble(mProtein);
        parcel.writeDouble(mChloride);
        parcel.writeDouble(mCholesterol);
        parcel.writeDouble(mCopper);
        parcel.writeDouble(mIodine);
        parcel.writeDouble(mVitaminB12);
        parcel.writeDouble(mZinc);
        parcel.writeDouble(mRiboflavin);
        parcel.writeDouble(mEnergy);
        parcel.writeDouble(mMolybdenum);
        parcel.writeDouble(mPhosphorus);
        parcel.writeDouble(mChromium);
        parcel.writeDouble(mTotalFat);
        parcel.writeDouble(mCalcium);
        parcel.writeDouble(mVitaminC);
        parcel.writeDouble(mVitaminE);
        parcel.writeDouble(mBiotin);
        parcel.writeDouble(mVitaminD);
        parcel.writeDouble(mNiacin);
        parcel.writeDouble(mMagnesium);
        parcel.writeDouble(mTotalCarbohydrate);
        parcel.writeDouble(mVitaminK);
        parcel.writeDouble(mPolyunsaturatedFat);
        parcel.writeDouble(mSaturatedFat);
        parcel.writeDouble(mSodium);
        parcel.writeDouble(mFolate);
        parcel.writeDouble(mMonounsaturatedFat);
        parcel.writeDouble(mPantothenicAcid);
        parcel.writeString(mMealName);
        parcel.writeDouble(mIron);
        parcel.writeDouble(mVitaminA);
        parcel.writeDouble(mFolicAcid);
        parcel.writeDouble(mSugar);
    }
}