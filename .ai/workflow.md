# Workflow

Use docs to freeze ownership before building large feature areas.

## Current Development Flow

1. Check current source and canonical docs.
2. Confirm feature ownership.
3. Add or update the smallest useful module/screen slice.
4. Keep UI scaffolding minimal.
5. Move data behind repositories when persistence is needed.
6. Add Supabase tables only when the real data shape is known.
7. Run validation.
8. Update progress docs when behavior or architecture changes.
9. Update ADRs when durable architecture decisions change.
10. Update the architecture changelog when module boundaries, data flow, navigation policy, or engineering practice changes.

## Source of Truth Order

When code and docs conflict:

1. Runtime source/config wins for actual behavior.
2. Product docs win for ownership and product status.
3. Platform-local docs win for implementation details.
4. This `.ai` directory is only a concise orientation layer.

## Do Not Start Without Explicit Need

Do not create large future areas before a slice needs them:

- Full onboarding rebuild
- Health integrations
- Recovery
- Billing / Entitlement
- Community
- Challenges
- AI Coach runtime
- Full Supabase schema

Plan them in docs first, then implement vertical slices.
