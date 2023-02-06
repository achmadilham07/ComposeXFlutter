import 'package:flutter/material.dart';

import 'utils/string.dart';

class MyHomePageState extends ChangeNotifier {
  final GlobalKey<ScaffoldMessengerState> scaffoldMessengerState;
  final GlobalKey<ScaffoldState> scaffoldState;

  MyHomePageState(this.scaffoldMessengerState, this.scaffoldState);

  void onMenuClick() {
    scaffoldState.currentState?.openDrawer();
  }

  void onItemSelected(String title) {
    scaffoldState.currentState?.closeDrawer();
    scaffoldMessengerState.currentState?.showSnackBar(
      SnackBar(
        content: Text(comingSoon(title)),
        action: SnackBarAction(
          label: subscribeQuestion,
          onPressed: () {},
        ),
      ),
    );
  }
}
