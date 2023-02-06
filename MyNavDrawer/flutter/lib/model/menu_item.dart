import 'package:flutter/material.dart';

class MenuItem {
  final String title;
  final Icon icon;
  MenuItem({
    required this.title,
    required this.icon,
  });

  MenuItem copyWith({
    String? title,
    Icon? icon,
  }) {
    return MenuItem(
      title: title ?? this.title,
      icon: icon ?? this.icon,
    );
  }

  @override
  String toString() => 'MenuItem(title: $title, icon: $icon)';

  @override
  bool operator ==(covariant MenuItem other) {
    if (identical(this, other)) return true;

    return other.title == title && other.icon == icon;
  }

  @override
  int get hashCode => title.hashCode ^ icon.hashCode;
}
