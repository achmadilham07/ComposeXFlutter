import 'package:flutter/material.dart';

import 'colors.dart';

final darkColorPalette = ThemeData(
  colorScheme: const ColorScheme.dark(
    primary: navy,
    primaryContainer: purple700,
    secondary: teal200,
    onPrimary: chartreuse,
  ),
);

final lightColorPalette = ThemeData(
  colorScheme: const ColorScheme.light(
    primary: lightBlue,
    primaryContainer: purple700,
    secondary: teal200,
    onPrimary: navy,
  ),
);
