# Tnyx Profile & Settings Architecture Guide

यह दस्तावेज़ Tnyx ऐप में **Profile (Fitness Hub)** और **Settings (App Config)** के स्ट्रक्चर, नेविगेशन और मॉड्यूल ओनरशिप को परिभाषित करता है। यह गाइड हमारे "Modular Architecture" और "Feature-Owned Navigation" सिद्धांतों पर आधारित है।

**Last Updated:** 2026-06-27  
**Status:** Architecture Blueprint

---

## 1. Conceptual Split: Fitness Hub vs. App Config

Tnyx में हम Profile और Settings को दो अलग उद्देश्यों के लिए उपयोग करते हैं:

| Feature | Concept | Responsibility |
|---|---|---|
| **Profile** | **Fitness Hub** | User Journey, Progress, Achievements, and Health Shortcut Launcher. |
| **Settings** | **App Config** | Technical Preferences, Account Management, and App Behavior. |

---

## 2. Feature Folder Tree

### A. Profile Module (`:features:profile`)
प्रोफ़ाइल मॉड्यूल केवल उन चीज़ों का मालिक है जो यूज़र की "Fitness Identity" से जुड़ी हैं।

```text
features/profile/
├── navigation/
│   ├── ProfileNavGraph.kt      # Launcher routes to other features
│   └── ProfileDestination.kt   # Internal routes (Journey, Photos)
└── presentation/
    ├── home/                   # The Main Dashboard (Launcher UI)
    ├── journey/                # Training history & milestones
    ├── progress_photos/        # Visual progress tracking
    ├── rewards/                # Gamification & Badges
    ├── resources/              # Educational content
    └── shared/widgets/         # Reusable profile UI elements
```

### B. Settings Module (`:features:settings`)
यह मॉड्यूल ऐप के व्यवहार और अकाउंट लेवल की सेटिंग्स को हैंडल करता है।

```text
features/settings/
├── navigation/
│   └── SettingsNavGraph.kt
└── presentation/
    ├── home/                   # Main settings list (Gear icon entry)
    ├── personal_info/          # Basic data (Name, Age, Gender)
    ├── subscription/           # Plan management (Free/Pro)
    ├── app_preferences/        # Theme, Language, Notifications
    ├── units/                  # Metric/Imperial toggles
    ├── export_data/            # JSON/CSV data export
    └── about/                  # Terms, Privacy, Version info
```

---

## 3. Cross-Feature Integration (Linking)

"Feature Ownership" नियम के अनुसार, सेटिंग्स जो किसी विशिष्ट डोमेन से संबंधित हैं, वे उसी फीचर मॉड्यूल में रहेंगी। प्रोफ़ाइल स्क्रीन केवल उन तक नेविगेट करेगी।

### Nutrition Domain (owned by `:features:nutrition`)
- **Nutrition Targets:** Calories, Macros, Water Goal, **Glass Size**, Steps, Sleep.
- **Why:** यह सारा डेटा न्यूट्रिशन कैलकुलेशन और मील डायरी को प्रभावित करता है।

### Workout Domain (owned by `:features:workout`)
- **Workout Settings:** Rest Timer, Warm-up, Plate Calculator, RPE, **Graph Settings**.
- **Why:** यह डेटा सीधे वर्कआउट लॉगिंग और एनालिटिक्स से जुड़ा है।

---

## 4. UI Entry Points & Navigation Flow

### Profile Dashboard Structure
प्रोफ़ाइल स्क्रीन एक **"Smart Launcher"** की तरह काम करेगी:

1. **Header:** Profile Card (Name, Streak, Current Plan).
2. **Visual Progress:** Journey & Progress Photos cards.
3. **Quick Actions (Sectioned List):**
   - 🍎 **Nutrition Targets** → `navigate(NutritionRoute.Targets)`
   - 🏋️ **Workout Settings** → `navigate(WorkoutRoute.Settings)`
   - 🔗 **Health Connections** → `navigate(HealthRoute.Home)`
   - ⭐ **Subscription** → `navigate(SettingsRoute.Subscription)`

### Settings Gear Icon
यह प्रोफ़ाइल स्क्रीन के **Top-Right** में रहेगा।
- **Entry:** `navigate(SettingsRoute.Home)`
- **Content:** Technical preferences (Notifications, Sound, Export, Legal).

---

## 5. Coding Rules for Profile & Settings

1. **No Data Duplication:** `Personal Information` (Settings में) और `Onboarding Data` का सोर्स ऑफ़ ट्रुथ एक ही होना चाहिए।
2. **Dumb UI:** प्रोफ़ाइल और सेटिंग्स की सभी स्क्रीन `UiState` पर चलेंगी।
3. **Module Independence:** `:features:profile` को `:features:nutrition` के इंटरनल `widgets` का उपयोग नहीं करना चाहिए; यह केवल टाइप-सेफ रूट के माध्यम से नेविगेट करेगा।
4. **Subscription Logic:** चूंकि सब्सक्रिप्शन पूरे ऐप के "Feature Access" को प्रभावित करता है, इसलिए इसका स्टेटस `AuthViewModel` या `GlobalState` में रहना चाहिए, लेकिन इसका मैनेजमेंट (UI) `Settings` के अंदर रहेगा।

---

*Maintainer: TNYX Lead Architect*
