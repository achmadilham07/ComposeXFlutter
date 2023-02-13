import 'package:flutter/material.dart';

import 'color.dart';

final darkColorPalette = ThemeData(
  colorScheme: const ColorScheme.dark(
    primary: lightCoffeeBrown,
    primaryContainer: lightCoffeeBrown,
    secondary: teal200,
  ),
);

final lightColorPalette = ThemeData(
  colorScheme: const ColorScheme.light(
    primary: coffeeBrown,
    primaryContainer: coffeeBrown,
    secondary: teal200,
  ),
);
