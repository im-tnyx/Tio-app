# ADR-0003: Type-Safe Navigation And Chrome Policy

## Status

Accepted

## Context

TNYX navigation is expected to exceed 100 screens.

String routes and shell-level special cases would become fragile as nested feature graphs, modal flows, auth, onboarding, settings, profile, and future tablet support grow.

## Decision

Use type-safe Compose Navigation route contracts for new navigation.

Root graph owns high-level flow:

- Auth
- Onboarding
- Main
- Modal

Main Graph contains:

- Home
- Workout
- Nutrition
- Coach
- Progress

Profile Graph opens from the avatar.

Settings Graph opens from the gear icon.

Every destination declares one chrome policy:

- `MainChrome`
- `NoBottomBar`
- `FullScreen`
- `BottomSheet`
- `Dialog`

`MainShell` must not contain feature-specific UI logic.

## Consequences

Positive:

- Back-stack behavior stays predictable.
- Feature graphs can evolve independently.
- Chrome behavior is explicit.
- Modal and full-screen flows can scale without shell hacks.

Negative:

- Every feature needs public route contracts.
- Chrome policy needs discipline during screen creation.
- Existing placeholder routes may need cleanup as real flows are implemented.
