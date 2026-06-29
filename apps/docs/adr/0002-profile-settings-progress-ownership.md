# ADR-0002: Profile Settings Progress Ownership

## Status

Accepted

## Context

Profile, Settings, Journey, Rewards, Resources, Progress Photos, Measurements, Subscription, and Personal Information can easily overlap in a large fitness app.

If Profile becomes a business domain, it will absorb logic from Progress, Billing, Health, Nutrition, and Settings.

## Decision

Profile is a Fitness Hub + Account Launcher, not a business domain.

Progress owns:

- Journey
- Progress Photos
- Measurements
- Weight
- Achievements
- Progress Analytics

Settings owns app/account configuration entry points.

Subscription business logic belongs to future Billing / Entitlement ownership.

Personal Information belongs to the Profile domain as a single source of truth shared by Onboarding, Profile, and Settings.

## Consequences

Positive:

- Profile remains lightweight and scalable.
- Progress can grow without leaking into account settings.
- Subscription and entitlement logic can become centralized later.
- Onboarding, Profile, and Settings can share personal information without duplicating state.

Negative:

- Profile cards require cross-feature route contracts.
- Ownership must be checked before adding new Profile shortcuts.
- Future Billing / Entitlement needs a clear module before subscription logic becomes real.
