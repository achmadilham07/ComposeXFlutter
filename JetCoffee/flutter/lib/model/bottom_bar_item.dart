import 'package:flutter/material.dart';

class BottomBarItem {
  final String title;
  final Icon icon;
  BottomBarItem({
    required this.title,
    required this.icon,
  });

  BottomBarItem copyWith({
    String? title,
    Icon? icon,
  }) {
    return BottomBarItem(
      title: title ?? this.title,
      icon: icon ?? this.icon,
    );
  }

  @override
  String toString() => 'BottomBarItem(title: $title, icon: $icon)';

  @override
  bool operator ==(covariant BottomBarItem other) {
    if (identical(this, other)) return true;

    return other.title == title && other.icon == icon;
  }

  @override
  int get hashCode => title.hashCode ^ icon.hashCode;
}
