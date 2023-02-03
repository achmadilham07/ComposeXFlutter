import 'package:flutter/material.dart';
import 'package:jet_coffee/widget/search_bar.dart';

void main() {
  runApp(const JetCoffeeApp());
}

class JetCoffeeApp extends StatelessWidget {
  const JetCoffeeApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: const [
          Banner()
        ],
      ),
    );
  }
}

class Banner extends StatelessWidget {
  const Banner({super.key});

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: [
        SizedBox(
          height: 160,
          width: double.infinity,
          child: Image.asset(
            "assets/banner.webp",
            semanticLabel: "Banner Image",
            fit: BoxFit.fitWidth,
          ),
        ),
        const SearchBar(),
      ],
    );
  }
}
