---
name: Fintech Precision
colors:
  surface: '#faf8ff'
  surface-dim: '#d2d9f4'
  surface-bright: '#faf8ff'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#f2f3ff'
  surface-container: '#eaedff'
  surface-container-high: '#e2e7ff'
  surface-container-highest: '#dae2fd'
  on-surface: '#131b2e'
  on-surface-variant: '#43474d'
  inverse-surface: '#283044'
  inverse-on-surface: '#eef0ff'
  outline: '#74777e'
  outline-variant: '#c4c6ce'
  surface-tint: '#49607e'
  primary: '#000f22'
  on-primary: '#ffffff'
  primary-container: '#0a2540'
  on-primary-container: '#768dad'
  inverse-primary: '#b0c8eb'
  secondary: '#006c4a'
  on-secondary: '#ffffff'
  secondary-container: '#82f5c1'
  on-secondary-container: '#00714e'
  tertiary: '#030046'
  on-tertiary: '#ffffff'
  tertiary-container: '#0a0085'
  on-tertiary-container: '#797dff'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#d2e4ff'
  primary-fixed-dim: '#b0c8eb'
  on-primary-fixed: '#001c37'
  on-primary-fixed-variant: '#314865'
  secondary-fixed: '#85f8c4'
  secondary-fixed-dim: '#68dba9'
  on-secondary-fixed: '#002114'
  on-secondary-fixed-variant: '#005137'
  tertiary-fixed: '#e1e0ff'
  tertiary-fixed-dim: '#c0c1ff'
  on-tertiary-fixed: '#07006c'
  on-tertiary-fixed-variant: '#2f2ebe'
  background: '#faf8ff'
  on-background: '#131b2e'
  surface-variant: '#dae2fd'
typography:
  display-hero:
    fontFamily: Inter
    fontSize: 40px
    fontWeight: '700'
    lineHeight: 48px
  display-hero-mobile:
    fontFamily: Inter
    fontSize: 32px
    fontWeight: '700'
    lineHeight: 40px
  balance-lg:
    fontFamily: Inter
    fontSize: 36px
    fontWeight: '600'
    lineHeight: 44px
  balance-lg-mobile:
    fontFamily: Inter
    fontSize: 28px
    fontWeight: '600'
    lineHeight: 36px
  headline-md:
    fontFamily: Inter
    fontSize: 24px
    fontWeight: '600'
    lineHeight: 32px
  headline-sm:
    fontFamily: Inter
    fontSize: 20px
    fontWeight: '600'
    lineHeight: 28px
  title-md:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '600'
    lineHeight: 24px
  body-lg:
    fontFamily: Inter
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-md:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  body-sm:
    fontFamily: Inter
    fontSize: 12px
    fontWeight: '400'
    lineHeight: 16px
  label-numeric:
    fontFamily: Inter
    fontSize: 14px
    fontWeight: '600'
    lineHeight: 20px
  label-caps:
    fontFamily: Inter
    fontSize: 11px
    fontWeight: '700'
    lineHeight: 14px
  caption:
    fontFamily: Inter
    fontSize: 11px
    fontWeight: '400'
    lineHeight: 14px
rounded:
  sm: 0.25rem
  DEFAULT: 0.5rem
  md: 0.75rem
  lg: 1rem
  xl: 1.5rem
  full: 9999px
spacing:
  gutter: 1rem
  gutter-tablet: 1.5rem
  margin: 1rem
  margin-tablet: 2rem
  space-2xs: 0.25rem
  space-xs: 0.5rem
  space-sm: 0.75rem
  space-md: 1rem
  space-lg: 1.5rem
  space-xl: 2rem
  space-2xl: 3rem
---

## Brand & Style

The design system is constructed for a native personal finance experience across iOS and Android, balancing institutional security with modern consumer clarity. It positions the interface not merely as a ledger, but as an intelligent, high-trust financial advisor.

### Emotional Profile & Target Audience
- **Target Audience:** Modern professionals, digital-native investors, and disciplined savers seeking frictionless clarity, rigorous transaction tracking, and calm financial oversight.
- **Emotional Response:** Quiet confidence, uncompromising security, precision, and cognitive lightness. The interface deliberately avoids visual noise or gamified tropes to foster focus, transparency, and trust.

### Visual Style
- **Corporate Minimalist with Modern Depth:** Clean geometry, deliberate whitespace, and structured modularity.
- **Layered Tactility:** Soft, ambient shadows combined with structural low-contrast borders (`#E2E8F0`) prevent floating ambiguities and anchor financial data to discrete physical cards.
- **Data-First Focus:** Strict tabular alignment and micro-interactions prioritize the legible presentation of monetary values.

## Colors

The palette establishes an immediate sense of institutional stability anchored by deep obsidian navy, with crisp emerald and subtle indigo providing clear semantic meaning.

### Role Allocations & Hierarchy
- **Primary (`#0A2540`):** Represents institutional authority, structural headers, active navigation anchors, and primary interaction triggers.
- **Primary Variant / Light (`#E0F2FE`):** Used for soft badge backgrounds, selection highlights, and contextual callouts.
- **Secondary (`#059669`):** Accent and indicator of positive equity growth, income cashflow, and verified transactions. Paired with soft emerald tint (`#D1FAE5`) for low-friction positive feedback.
- **Tertiary (`#6366F1`):** Applied selectively to analytics projections, category distribution graphs, and automated investment tags.
- **Neutral Primary (`#0F172A`):** The primary text and foreground anchor, ensuring WCAG AAA legibility against white and slate surfaces.
- **Neutral Secondary (`#64748B`):** Supporting metadata, timestamps, input labels, and inactive tab states.
- **Surfaces (`#FFFFFF`, `#F8FAFC`, `#F1F5F9`):** Multi-tier surface system separating global canvas (`#F8FAFC`) from interactive modules (`#FFFFFF`) and inner grouping insets (`#F1F5F9`).
- **Semantic Feedback:**
  - Success: `#10B981` (Completed transfers, balance increases)
  - Warning: `#F59E0B` (Budget limits approaching, pending holds)
  - Error: `#EF4444` (Declined transactions, overdrawn alerts)
  - Disabled: Border `#CBD5E1` / Foreground `#94A3B8`

## Typography

The typographic hierarchy uses Inter (with native platform fallbacks to SF Pro on iOS and Roboto on Android) to ensure crisp micro-scale rendering and clean numerical figures.

### Typographic Rules & Numerical Handling
- **Tabular Figures (`tnum`):** All financial values, percentages, balances, transaction tables, and metrics must enforce OpenType `font-feature-settings: "tnum", "cv02", "cv03"`. This prevents horizontal jitter during balance updates and keeps decimal points vertically aligned across rows.
- **Currency Symbols:** Currency symbols (`$`, `€`, `£`) share the parent numerical weight but scale down by one level (or are styled at 80% baseline offset) to keep visual focus on the magnitude itself.
- **Category Labels:** `label-caps` uses all-caps styling with `letter-spacing: 0.06em` to distinguish metadata from content.

## Layout & Spacing

The layout is built on a strict 4px/8px incremental grid, ensuring native alignment across iOS screen scales (`@2x`, `@3x`) and Android density buckets (`xhdpi`, `xxhdpi`).

### Mobile Native Shell
- **Outer Canvas Margins:** 16px (`1rem`) on standard compact mobile screens (up to 390px), expanding to 20px on modern large devices (Pro Max/Plus) and 32px (`2rem`) on foldables/tablets.
- **Safe Area Insets:** Content must strictly respect the notch/Dynamic Island top insets (44px–59px) and bottom home indicator bar (34px), placing actions in reachable thumb zones.
- **Vertical Rhythm:**
  - Card internal padding: 16px (`space-md`) for summary modules, 20px for primary hero balance cards.
  - Inter-card vertical gap: 12px (`space-sm`) to 16px (`space-md`).
  - Section headers to card stacks: 8px (`space-xs`).
  - Screen edge margins: Never drop below 16px to prevent accidental edge taps.

## Elevation & Depth

Visual hierarchy uses a hybrid structure: soft surface boundaries (`#E2E8F0`) combined with diffused, low-opacity ambient shadows tinted in primary navy. This eliminates harsh separation while keeping cards distinct over the `#F8FAFC` base.

### Elevation Levels
- **Level 0 (Flat / Canvas):** Used for background canvas (`#F8FAFC`). No shadow, no border.
- **Level 1 (Base Cards & Transaction Lists):** Surface `#FFFFFF`, 1px solid `#E2E8F0`, shadow: `0px 1px 3px rgba(15, 23, 42, 0.04), 0px 1px 2px rgba(15, 23, 42, 0.02)`.
- **Level 2 (Interactive Floating / Active Filter Chips):** Surface `#FFFFFF`, 1px solid `#E2E8F0`, shadow: `0px 4px 6px -1px rgba(15, 23, 42, 0.06), 0px 2px 4px -2px rgba(15, 23, 42, 0.04)`.
- **Level 3 (Sticky Headers / Bottom Navigation Bar):** Surface `#FFFFFF` with 94% opacity and `backdrop-filter: blur(12px)`. Subtle bottom/top divider: `1px solid #E2E8F0`. Shadow: `0px -4px 12px rgba(15, 23, 42, 0.03)`.
- **Level 4 (Modals, Overlays & Bottom Sheets):** Surface `#FFFFFF`, shadow: `0px -8px 24px rgba(10, 37, 64, 0.12)`. Scrim overlay uses `#0F172A` at 40% opacity with a slight blur to isolate focus on confirmation steps.

## Shapes

The design system applies a disciplined corner radius scale that mirrors native iOS and Material 3 design languages.

### Corner Radius Mapping
- **Input Fields & Buttons (`12px`):** Applied to buttons, text fields, search bars, and dropdown triggers for accessible tap targets with balanced ergonomics.
- **Financial Content Cards (`16px`):** Used for account summaries, net-worth monitors, credit limit cards, and transaction group containers.
- **Bottom Sheets & Modal Dialogs (`24px` top corners):** Used for action sheets, transaction drawer details, and bottom-up payment flows.
- **Badges, Tags & Numeric Pills (`9999px` / Full Pill):** Used for status tags (`+2.4%`, `Pending`, `Settled`), category filters, and quick-action icon wrappers.

## Components

### Buttons
- **Primary:** Background `#0A2540`, text `#FFFFFF`, 12px corner radius. Minimum height 48px (optimized for 44px+ touch targets). Active state drops opacity to 0.92 with a subtle scale down (`transform: scale(0.98)`).
- **Secondary / Ghost:** Background `#F1F5F9`, text `#0A2540`, borderless. Hover/press transitions to `#E2E8F0`.
- **Destructive:** Background `#FEE2E2`, text `#EF4444`, 1px solid `#FCA5A5`. Used for account disconnection or card cancellation.

### Input Fields
- Height 52px, corner radius 12px.
- Idle: Background `#FFFFFF`, border 1px solid `#E2E8F0`, text `#0F172A`, placeholder `#94A3B8`.
- Focus: Border 1.5px solid `#0A2540`, subtle ring `0 0 0 3px rgba(10, 37, 64, 0.08)`.
- Financial Amount Input: Large display input (32px typography), auto-scaling font size with dynamic currency prefix fixed to the left.

### Financial Summary Cards
- Background `#FFFFFF`, border 1px solid `#E2E8F0`, corner radius 16px, padding 16px or 20px.
- Contains an account badge, localized currency value in `tnum` font features, and an inline trend chip (`+4.2%` in `#059669` on `#D1FAE5` pill).

### Lists & Transaction Rows
- Height: 64px row item.
- Left: 40px circular category icon surface (`#F1F5F9` with `#0A2540` iconography).
- Center: Double-line metadata (Merchant / Title in `title-md`, timestamp / payment method in `body-sm` `#64748B`).
- Right: Monetary amount aligned right (`label-numeric`), colored `#0F172A` for expenses, `#059669` with `+` sign for credits.

### Chips & Category Filters
- Height: 32px, corner radius 9999px (full pill).
- Inactive: Background `#F1F5F9`, text `#64748B`, border 1px solid transparent.
- Active: Background `#0A2540`, text `#FFFFFF`, border 1px solid `#0A2540`.

### Checkboxes & Switches
- Native switch toggle scaled to 51x31px (iOS style) with `#059669` track on active, `#CBD5E1` on inactive. Thumb `#FFFFFF` with drop shadow.
- Checkboxes: 20x20px, 6px radius, filled with `#0A2540` containing an optical white check icon.

### Bottom Sheet (Transaction Detail & Transfer Flow)
- Top border radius 24px, drag indicator pill (`#CBD5E1`, 36x4px, centered at 8px from top).
- Padding: 24px horizontal. Includes contextual breakdown: interchange fee, category switcher, receipt attachment thumbnail, and dispute button.