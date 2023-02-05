import 'package:flutter/material.dart';

import 'model/bottom_bar_item.dart';
import 'model/category.dart';
import 'model/menu.dart';
import 'utils/color.dart';
import 'utils/string.dart';
import 'utils/theme.dart';
import 'widget/category_item.dart';
import 'widget/home_section.dart';
import 'widget/menu_item.dart';
import 'widget/search_bar.dart';

void main() {
  runApp(const JetCoffeeApp());
}

class JetCoffeeApp extends StatelessWidget {
  const JetCoffeeApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: lightColorPalette,
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
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          mainAxisAlignment: MainAxisAlignment.start,
          mainAxisSize: MainAxisSize.min,
          children: [
            const Banner(),
            const HomeSection(
              title: sectionCategory,
              content: CategoryRow(),
            ),
            HomeSection(
              title: sectionFavoriteMenu,
              content: MenuRow(listMenu: dummyMenu),
            ),
            HomeSection(
              title: sectionBestSellerMenu,
              content: MenuRow(listMenu: dummyBestSellerMenu),
            ),
          ],
        ),
      ),
      bottomNavigationBar: const BottomBar(),
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
      height: 160,
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

class MenuRow extends StatelessWidget {
  final List<Menu> listMenu;
  const MenuRow({
    super.key,
    required this.listMenu,
  });

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 200,
      child: ListView.separated(
        scrollDirection: Axis.horizontal,
        padding: const EdgeInsets.symmetric(horizontal: 8),
        itemBuilder: (context, index) {
          final menu = listMenu[index];
          return MenuItem(menu: menu);
        },
        separatorBuilder: (context, index) {
          return const SizedBox(width: 8);
        },
        itemCount: listMenu.length,
      ),
    );
  }
}

class BottomBar extends StatelessWidget {
  const BottomBar({super.key});

  @override
  Widget build(BuildContext context) {
    final navigationItems = [
      BottomBarItem(
        title: "Home",
        icon: const Icon(Icons.home),
      ),
      BottomBarItem(
        title: "Favorite",
        icon: const Icon(Icons.favorite),
      ),
      BottomBarItem(
        title: "Profile",
        icon: const Icon(Icons.account_circle),
      ),
    ];
    return BottomNavigationBar(
      onTap: (index) {},
      backgroundColor: Theme.of(context).colorScheme.background,
      selectedItemColor: Theme.of(context).colorScheme.primary,
      unselectedItemColor: lightGray,
      items: navigationItems
          .map(
            (e) => BottomNavigationBarItem(icon: e.icon, label: e.title),
          )
          .toList(),
    );
  }
}
