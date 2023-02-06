
import 'package:flutter/material.dart';

import 'utils/string.dart';

class MyTopBar extends StatelessWidget implements PreferredSizeWidget {
  final Function() onMenuClick;

  const MyTopBar({
    super.key,
    required this.onMenuClick,
  });

  @override
  Widget build(BuildContext context) {
    return AppBar(
      leading: IconButton(
        onPressed: () {
          onMenuClick();
        },
        icon: const Icon(Icons.menu),
        tooltip: menu,
      ),
      title: const Text(appName),
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
}
