import 'package:flutter/material.dart';

import '../model/category.dart';

class CategoryItem extends StatelessWidget {
  final Category category;
  const CategoryItem({
    super.key,
    required this.category,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      children: [
        CircleAvatar(
          radius: 40,
          backgroundColor: Colors.transparent,
          child: Image.asset(
            category.imageCategory,
          ),
        ),
        Padding(
          padding: const EdgeInsets.only(top: 16, bottom: 8),
          child: Text(
            category.textCategory,
            style: const TextStyle(
              fontSize: 17,
            ),
          ),
        ),
      ],
    );
  }
}
