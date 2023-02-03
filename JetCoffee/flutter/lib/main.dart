import 'package:flutter/material.dart';
import 'package:jet_coffee/model/category.dart';
import 'package:jet_coffee/utils/string.dart';
import 'package:jet_coffee/widget/category_item.dart';
import 'package:jet_coffee/widget/search_bar.dart';
import 'package:jet_coffee/widget/section_text.dart';

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
        crossAxisAlignment: CrossAxisAlignment.stretch,
        mainAxisAlignment: MainAxisAlignment.start,
        mainAxisSize: MainAxisSize.min,
        children: const [
          Banner(),
          SectionText(title: sectionCategory),
          CategoryRow(),
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

class CategoryRow extends StatelessWidget {
  const CategoryRow({super.key});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 200,
      child: ListView.separated(
        padding: const EdgeInsets.all(16),
        itemCount: dummyCategory.length,
        scrollDirection: Axis.horizontal,
        shrinkWrap: true,
        primary: false,
        itemBuilder: (context, index) {
          final category = dummyCategory[index];
          return CategoryItem(
            category: category,
          );
        },
        separatorBuilder: (context, index) {
          return const SizedBox(width: 8);
        },
      ),
    );
  }
}
