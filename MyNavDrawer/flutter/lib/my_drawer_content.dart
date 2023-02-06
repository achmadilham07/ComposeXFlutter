
import 'package:flutter/material.dart';

import 'model/menu_item.dart';
import 'utils/string.dart';

class MyDrawerContent extends StatelessWidget {
  final Function(String title) onItemSelected;

  const MyDrawerContent({
    super.key,
    required this.onItemSelected,
  });

  @override
  Widget build(BuildContext context) {
    final items = [
      MenuItem(
        title: home,
        icon: const Icon(Icons.home),
      ),
      MenuItem(
        title: favourite,
        icon: const Icon(Icons.favorite),
      ),
      MenuItem(
        title: profile,
        icon: const Icon(Icons.account_circle),
      ),
    ];
    return Column(
      mainAxisSize: MainAxisSize.max,
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        Container(
          height: 190,
          color: Theme.of(context).colorScheme.primary,
        ),
        for (var item in items)
          ListTile(
            title: Text(item.title),
            leading: item.icon,
            onTap: () {
              onItemSelected(item.title);
            },
          ),
      ],
    );
  }
}
