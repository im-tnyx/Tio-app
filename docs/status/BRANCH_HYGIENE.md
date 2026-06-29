# Branch Hygiene Status

## Canonical Branch

`main` is the canonical development branch for this repository.

`origin/HEAD` points to `origin/main`.

## Legacy Local `master`

A local `master` branch existed with the following commit:

- `5642626 refactor: restructure for Turborepo monorepo`

This branch is legacy/outdated and should not be used as a source branch for new work.

## Recovery Review

The useful restructure work from `master` was already present on `main` through the equivalent restructure commit in `main` history.

The local `master` branch did not contain a missing Turborepo implementation. Root Turborepo workspace files such as `package.json`, `turbo.json`, workspace config, and lockfiles were not present in that branch.

Therefore:

- Do not merge `master` into `main`.
- Do not cherry-pick `5642626`.
- Treat `main` as the source of truth.

## Turborepo Status

Turborepo workspace tooling is not currently implemented by this cleanup.

Future Turborepo work should be introduced through a dedicated PR, for example:

```text
chore(repo): add Turborepo workspace foundation
```

That future PR should intentionally define:

* package manager choice
* root workspace config
* `turbo.json`
* CI task wiring
* Android/Wear Gradle relationship to Node workspace scripts
* generated artifact ignores

## Cleanup Decision

After documenting this status and confirming no useful changes are missing, the local `master` branch can be deleted.

This is a local branch cleanup only. No remote branch deletion is required unless a remote `origin/master` exists and has been explicitly reviewed.
