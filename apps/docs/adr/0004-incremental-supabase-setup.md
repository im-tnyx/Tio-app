# ADR-0004: Incremental Supabase Setup

## Status

Accepted

## Context

The Supabase project currently starts with minimal/blank configuration.

Creating every table before the Android feature slices are clear would likely produce wrong schemas, stale demo data, and future migration churn.

## Decision

Use incremental Supabase setup.

Create tables, RLS, RPCs, seeds, and repository wiring only when a feature slice needs persistent data.

Recommended flow:

1. Build the smallest useful UI slice.
2. Identify the real data shape.
3. Add the minimum table/RPC.
4. Add RLS.
5. Add demo seed data.
6. Connect through repositories.
7. Test the feature end to end.

Hardcoded data is allowed only as temporary scaffolding.

## Consequences

Positive:

- Database shape follows real product needs.
- Demo data becomes useful and testable.
- RLS can be designed per feature.
- The app avoids long-lived hardcoded source-of-truth data.

Negative:

- Early screens may temporarily use scaffolding.
- Each feature slice needs explicit data review.
- CI and documentation must clearly distinguish planned schema from applied schema.
