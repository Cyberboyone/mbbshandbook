#!/usr/bin/env python3
"""Regenerate legacy launcher PNGs and the 512px store icon from the vector mark.

Requires: pip install resvg-py
Run from repo root: python3 scripts/generate_icons.py
"""
from pathlib import Path

import resvg_py

ROOT = Path(__file__).resolve().parents[1]
RES = ROOT / "app/src/main/res"
STORE = ROOT / "store-listing"

DEFS = """
<defs>
  <linearGradient id="bg" x1="0" y1="0" x2="108" y2="108" gradientUnits="userSpaceOnUse">
    <stop offset="0" stop-color="#0B6E4F"/>
    <stop offset="1" stop-color="#148F63"/>
  </linearGradient>
</defs>
"""

FOREGROUND = """
<g>
  <path fill="#FFFFFF" d="M54 46 C46 39 34 36 24 38 L24 74 C34 72 46 75 54 82 Z"/>
  <path fill="#FFFFFF" d="M54 46 C62 39 74 36 84 38 L84 74 C74 72 62 75 54 82 Z"/>
  <g fill="none" stroke="#9FD8C3" stroke-linecap="round">
    <path stroke-width="1.6" d="M54 46 L54 82"/>
    <path stroke-width="1.8" d="M30 49 C37 48 45 50 50 53"/>
    <path stroke-width="1.8" d="M30 58 C37 57 45 59 50 62"/>
    <path stroke-width="1.8" d="M30 67 C37 66 45 68 50 71"/>
    <path stroke-width="1.8" d="M58 53 C64 50 71 48 78 49"/>
    <path stroke-width="1.8" d="M58 62 C64 59 71 57 78 58"/>
  </g>
  <g fill="none" stroke="#0B3B2E" stroke-width="3.4" stroke-linecap="round" stroke-linejoin="round">
    <path d="M38 27 L38 40 C38 48 44 53 52 53"/>
    <path d="M68 27 L68 40 C68 48 62 53 52 53"/>
    <path d="M52 53 C52 64 58 70 66 70 C72 70 76 66 76 61"/>
  </g>
  <circle cx="38" cy="26" r="3" fill="#F4B942"/>
  <circle cx="68" cy="26" r="3" fill="#F4B942"/>
  <circle cx="76" cy="58" r="6.5" fill="#F4B942" stroke="#0B3B2E" stroke-width="2"/>
  <circle cx="76" cy="58" r="2.5" fill="#0B3B2E"/>
</g>
"""


def svg(background: str) -> str:
    return (
        '<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 108 108">'
        + DEFS
        + background
        + FOREGROUND
        + "</svg>"
    )


SQUARE = svg('<rect x="0" y="0" width="108" height="108" rx="24" fill="url(#bg)"/>')
ROUND = svg('<circle cx="54" cy="54" r="54" fill="url(#bg)"/>')
STORE_SVG = svg('<rect x="0" y="0" width="108" height="108" fill="url(#bg)"/>')


def render(markup: str, size: int, out: Path) -> None:
    out.parent.mkdir(parents=True, exist_ok=True)
    out.write_bytes(
        resvg_py.svg_to_bytes(
            svg_string=markup,
            width=size,
            height=size,
            shape_rendering="geometric_precision",
        )
    )
    print(f"{out.relative_to(ROOT)} ({size}x{size})")


def main() -> None:
    densities = {"mdpi": 48, "hdpi": 72, "xhdpi": 96, "xxhdpi": 144, "xxxhdpi": 192}
    for density, size in densities.items():
        folder = RES / f"mipmap-{density}"
        render(SQUARE, size, folder / "ic_launcher.png")
        render(ROUND, size, folder / "ic_launcher_round.png")
    render(STORE_SVG, 512, STORE / "icon-512.png")


if __name__ == "__main__":
    main()
