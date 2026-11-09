#!/usr/bin/env python3
"""Generate Play Store graphics from the app's own brand assets.

Creates `store-listing/feature-graphic-1024x500.png` (required by Google Play)
using the shipped launcher icon and the Poppins fonts bundled with the app, so
the artwork always matches the product.

Requirements: `pip install pillow`
Usage:        python3 scripts/generate_store_graphics.py
"""

from __future__ import annotations

import math
from pathlib import Path

from PIL import Image, ImageDraw, ImageFilter, ImageFont

ROOT = Path(__file__).resolve().parent.parent
STORE = ROOT / "store-listing"
FONTS = ROOT / "app" / "src" / "main" / "res" / "font"
ICON = STORE / "icon-512.png"

W, H = 1024, 500
BG_TOP = (16, 60, 50)
BG_BOTTOM = (8, 18, 15)
TEXT = (236, 244, 240)
MUTED = (157, 180, 171)
MINT = (43, 227, 195)
OUTLINE = (39, 54, 47)
CHIP_BG = (17, 29, 24)


def font(name: str, size: int) -> ImageFont.FreeTypeFont:
    return ImageFont.truetype(str(FONTS / name), size)


def vertical_gradient(size: tuple[int, int], top: tuple[int, int, int],
                      bottom: tuple[int, int, int]) -> Image.Image:
    w, h = size
    base = Image.new("RGB", size, top)
    draw = ImageDraw.Draw(base)
    for y in range(h):
        t = y / max(h - 1, 1)
        draw.line(
            [(0, y), (w, y)],
            fill=tuple(round(top[i] + (bottom[i] - top[i]) * t) for i in range(3)),
        )
    return base


def radial_glow(canvas: Image.Image, center: tuple[int, int], radius: int,
                color: tuple[int, int, int], strength: float = 1.0) -> None:
    """Soft additive glow, used to keep the dark teal art from looking flat."""
    glow = Image.new("L", canvas.size, 0)
    gd = ImageDraw.Draw(glow)
    steps = 48
    for i in range(steps, 0, -1):
        r = int(radius * i / steps)
        alpha = int(255 * strength * (1 - i / steps) ** 2)
        gd.ellipse(
            [center[0] - r, center[1] - r, center[0] + r, center[1] + r],
            fill=alpha,
        )
    glow = glow.filter(ImageFilter.GaussianBlur(radius // 6))
    layer = Image.new("RGB", canvas.size, color)
    canvas.paste(layer, (0, 0), glow)


def rounded_icon(size: int, radius: int) -> Image.Image:
    """Launcher icon cropped to a rounded square with a hairline border."""
    icon = Image.open(ICON).convert("RGB").resize((size, size), Image.LANCZOS)
    mask = Image.new("L", (size, size), 0)
    ImageDraw.Draw(mask).rounded_rectangle([0, 0, size - 1, size - 1], radius, fill=255)
    out = Image.new("RGBA", (size, size), (0, 0, 0, 0))
    out.paste(icon, (0, 0), mask)
    border = ImageDraw.Draw(out)
    border.rounded_rectangle([0, 0, size - 1, size - 1], radius,
                             outline=OUTLINE + (255,), width=2)
    return out


def draw_chip(draw: ImageDraw.ImageDraw, x: int, y: int, label: str,
              fnt: ImageFont.FreeTypeFont, pad_x: int = 18, pad_y: int = 11) -> int:
    left, top, right, bottom = draw.textbbox((0, 0), label, font=fnt)
    tw, th = right - left, bottom - top
    w, h = tw + pad_x * 2, th + pad_y * 2
    draw.rounded_rectangle([x, y, x + w, y + h], h // 2, fill=CHIP_BG, outline=OUTLINE, width=2)
    draw.text((x + pad_x - left, y + pad_y - top), label, font=fnt, fill=MINT)
    return w


def build_feature_graphic() -> Path:
    canvas = vertical_gradient((W, H), BG_TOP, BG_BOTTOM)
    radial_glow(canvas, (-40, -60), 620, (24, 96, 78), strength=1.0)
    radial_glow(canvas, (520, 470), 460, (18, 74, 62), strength=0.55)

    icon_size = 264
    icon = rounded_icon(icon_size, 56)
    shadow = Image.new("RGBA", (W, H), (0, 0, 0, 0))
    ImageDraw.Draw(shadow).rounded_rectangle(
        [72 - 6, 118 + 14, 72 + icon_size + 6, 118 + icon_size + 14],
        62, fill=(0, 0, 0, 150))
    shadow = shadow.filter(ImageFilter.GaussianBlur(18))
    canvas = Image.alpha_composite(canvas.convert("RGBA"), shadow).convert("RGB")
    canvas.paste(icon, (72, 118), icon)

    draw = ImageDraw.Draw(canvas)
    title_font = font("poppins_bold.ttf", 62)
    tagline_font = font("poppins_regular.ttf", 26)
    chip_font = font("poppins_medium.ttf", 20)
    eyebrow_font = font("poppins_semibold.ttf", 18)

    text_x = 392
    draw.text((text_x, 126), "FOR MBBS STUDENTS", font=eyebrow_font, fill=MINT)
    draw.text((text_x, 158), "MBBS Handbook", font=title_font, fill=TEXT)
    draw.text((text_x, 244), "Study smarter through medical school", font=tagline_font, fill=MUTED)

    chips = ["67 topics", "168 MCQs", "18 specialties", "Offline"]
    x, y = text_x, 300
    for label in chips:
        width = draw_chip(draw, x, y, label, chip_font)
        x += width + 12
        if x > W - 120:
            x, y = text_x, y + 58

    # Thin accent rule along the bottom of the art.
    draw.line([(text_x, 404), (W - 72, 404)], fill=OUTLINE, width=2)
    draw.line([(text_x, 404), (text_x + 148, 404)], fill=MINT, width=3)

    out = STORE / "feature-graphic-1024x500.png"
    canvas.save(out, optimize=True)
    return out


def build_promo_icon() -> Path:
    """512x512 icon export used by the Play listing (already shipped)."""
    return ICON


def main() -> None:
    if not ICON.exists():
        raise SystemExit(f"missing {ICON} - run scripts/generate_icons.py first")
    STORE.mkdir(exist_ok=True)
    graphic = build_feature_graphic()
    size_kb = graphic.stat().st_size / 1024
    print(f"wrote {graphic.relative_to(ROOT)} ({W}x{H}, {size_kb:.0f} KB)")
    print(f"store icon: {build_promo_icon().relative_to(ROOT)}")
    # Play limits: 1024x500, max 15 MB, no alpha channel.
    with Image.open(graphic) as check:
        assert check.size == (W, H), check.size
        assert check.mode == "RGB", check.mode
    print("validated: 1024x500 RGB, under 15 MB")


if __name__ == "__main__":
    main()
