import 'package:flutter/material.dart';
import 'package:my_nav_drawer/my_home_page_state.dart';
import 'package:provider/provider.dart';

import 'my_drawer_content.dart';
import 'my_top_bar.dart';
import 'utils/string.dart';

class MyHomePage extends StatelessWidget {
  const MyHomePage({super.key});

  @override
  Widget build(BuildContext context) {
    final MyHomePageState state = context.read<MyHomePageState>();
    return Scaffold(
      key: state.scaffoldState,
      appBar: MyTopBar(
        onMenuClick: () => state.onMenuClick(),
      ),
      drawer: Drawer(
        child: MyDrawerContent(
          onItemSelected: (title) => state.onItemSelected(title),
        ),
      ),
      drawerEnableOpenDragGesture:
          state.scaffoldState.currentState?.isDrawerOpen ?? false,
      body: const Center(child: Text(helloWorld)),
    );
  }
}
