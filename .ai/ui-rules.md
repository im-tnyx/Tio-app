# UI Rules

Use the existing TNYX Android UI system.

## Compose Rules

- Use `TnyxTheme`.
- Use existing design tokens and components where available.
- Do not hardcode feature-specific UI logic in the app shell.
- Do not put business logic in composable screens.
- Keep screens readable, stable, and responsive.
- Text must fit inside its container on mobile viewports.

## Chrome Policy

Every destination must declare its expected chrome behavior:

- `MainChrome`
- `NoBottomBar`
- `FullScreen`
- `BottomSheet`
- `Dialog`

`MainShell` must never contain feature-specific UI logic.

## Main Tabs

The main bottom navigation is:

- Home
- Workout
- Nutrition
- Coach
- Progress

Do not add Profile to the main bottom navigation.

Profile opens from the avatar.

Settings opens from the gear icon.

## Production Screen Checklist

Before a production screen is considered ready, define:

- UI owner
- Navigation owner
- Business logic owner
- Repository owner
- Chrome policy
- Empty state
- Loading state
- Error state
- Demo or real data source
