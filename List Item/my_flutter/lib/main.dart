import 'package:flutter/material.dart';

import 'themes.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'My Flutter',
      // theme: lightColorPalette,
      darkTheme: darkColorPalette,
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
  var name = "Jetpack Compose";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: GreetingList(sampleName),
    );
  }
}

final sampleName = [
  "Andre",
  "Desta",
  "Parto",
  "Wendy",
  "Komeng",
  "Raffi Ahmad",
  "Andhika Pratama",
  "Vincent Ryan Rompies"
];

class GreetingList extends StatelessWidget {
  final List<String> names;
  const GreetingList(
    this.names, {
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return names.isNotEmpty
        ? ListView.builder(
            itemCount: names.length,
            itemBuilder: (context, index) {
              final name = names[index];
              return Greeting(name);
            },
          )
        : const Center(
            child: Text("No People to great :("),
          );
  }
}

class Greeting extends StatefulWidget {
  final String name;

  const Greeting(this.name, {super.key});

  @override
  State<Greeting> createState() => _GreetingState();
}

class _GreetingState extends State<Greeting> {
  bool isExpanded = false;
  double sizeImage = 80;

  @override
  Widget build(BuildContext context) {
    return Card(
      color: Theme.of(context).colorScheme.primary,
      shape: const BeveledRectangleBorder(
        borderRadius: BorderRadius.only(
          topLeft: Radius.circular(16),
          bottomRight: Radius.circular(16),
        ),
      ),
      child: Padding(
        padding: const EdgeInsets.symmetric(vertical: 4, horizontal: 8),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            AnimatedSize(
              curve: Curves.elasticOut,
              duration: const Duration(seconds: 1),
              child: Image(
                image: const AssetImage(
                  "assets/jetpack_compose.png",
                ),
                width: sizeImage,
                height: sizeImage,
                semanticLabel: "Logo Jetpack Compose",
              ),
            ),
            const SizedBox(width: 8),
            Expanded(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    "Hallo ${widget.name}!",
                    style: const TextStyle(
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  Text(
                    "Welcome to Dicoding!",
                    style: Theme.of(context).textTheme.bodyMedium?.copyWith(
                          fontStyle: FontStyle.italic,
                        ),
                  ),
                ],
              ),
            ),
            IconButton(
              onPressed: () {
                setState(() {
                  isExpanded = !isExpanded;
                  sizeImage = isExpanded ? 120 : 80;
                });
              },
              icon: Icon(isExpanded ? Icons.expand_less : Icons.expand_more),
              tooltip: isExpanded ? "Show less" : "Show more",
            ),
          ],
        ),
      ),
    );
  }
}
