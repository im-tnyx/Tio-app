# ADR-0001: Android Modular Clean Architecture

## Status

Accepted

## Context

TNYX is expected to grow toward 100+ screens across Android, Wear OS, AI Coach, Nutrition, Workout, Progress, Recovery, Health integrations, Subscription, and future platforms.

A flat app module would make ownership, testing, and navigation difficult as the product grows.

## Decision

Use a Gradle multi-module Android architecture under `apps/`.

Primary responsibilities:

- `app`: Android entry point, root navigation, shell wiring, DI composition.
- `core`: design system, reusable UI, shell primitives, feature-agnostic routes/utilities.
- `shared`: pure Kotlin domain-safe contracts and models reused by phone/watch.
- `features/*`: feature-owned presentation, navigation, and feature logic.
- `wear`: Wear OS companion app.

Feature screens use:

- `Route`
- `Screen`
- `ViewModel`
- `UiState`
- `Action`

## Consequences

Positive:

- Feature ownership remains clear.
- Main app shell stays thin.
- Wear OS reuse remains possible through shared contracts.
- Large-screen and future platform decisions can build on stable boundaries.

Negative:

- More modules require more Gradle discipline.
- Shared models need careful ownership review before being promoted.
- Cross-feature navigation must go through explicit route contracts.
