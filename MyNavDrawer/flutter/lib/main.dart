import 'package:flutter/material.dart';
import 'package:my_nav_drawer/my_home_page_state.dart';
import 'package:provider/provider.dart';

import 'my_home_page.dart';

void main() {
  runApp(const MyNavDrawerApp());
}

final scaffoldMessengerKey = GlobalKey<ScaffoldMessengerState>();
final scaffoldState = GlobalKey<ScaffoldState>();

class MyNavDrawerApp extends StatelessWidget {
  const MyNavDrawerApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      scaffoldMessengerKey: scaffoldMessengerKey,
      home: ChangeNotifierProvider<MyHomePageState>(
        create: (context) => MyHomePageState(
          scaffoldMessengerKey,
          scaffoldState,
        ),
        child: const MyHomePage(),
      ),
    );
  }
}
