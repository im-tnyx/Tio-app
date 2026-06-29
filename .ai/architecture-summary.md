# Architecture Summary

TNYX Android uses a Gradle multi-module architecture with feature-owned modules.

The target shape is Clean Architecture with feature-level vertical slices.

## Core Rules

- App shell owns root navigation and chrome only.
- Feature modules own their routes, screens, view models, and feature logic.
- Screens are dumb UI.
- Business logic belongs in `ViewModel`, use cases, repositories, or domain services.
- Cross-feature navigation goes through public route contracts.
- Shared domain-safe models belong in shared modules only when reused across features or Wear OS.
- Do not move feature business logic into `app`, `core`, or shell code.

## Android UI Pattern

Use this pattern for feature screens:

- `Route`
- `Screen`
- `ViewModel`
- `UiState`
- `Action`

Compose screens must not perform network calls, repository writes, persistence, or mutation logic.

## Main Navigation

Main bottom tabs:

- Home
- Workout
- Nutrition
- Coach
- Progress

Profile is launched from the top-right avatar.

Settings is launched from the gear icon.

## Future Modules

Create modules only when runtime code needs them.

Do not create empty future modules for:

- Health
- Recovery
- Billing
- Entitlement
- Community
- Challenges
- Learn / Resources
- Rewards
- Analytics

Document the owner first, then add the module when the feature slice starts.
