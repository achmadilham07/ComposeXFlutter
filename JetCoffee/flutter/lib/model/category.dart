import 'dart:convert';

import '../utils/string.dart';

class Category {
  final String imageCategory;
  final String textCategory;
  Category({
    required this.imageCategory,
    required this.textCategory,
  });

  Category copyWith({
    String? imageCategory,
    String? textCategory,
  }) {
    return Category(
      imageCategory: imageCategory ?? this.imageCategory,
      textCategory: textCategory ?? this.textCategory,
    );
  }

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'imageCategory': imageCategory,
      'textCategory': textCategory,
    };
  }

  factory Category.fromMap(Map<String, dynamic> map) {
    return Category(
      imageCategory: map['imageCategory'] as String,
      textCategory: map['textCategory'] as String,
    );
  }

  String toJson() => json.encode(toMap());

  factory Category.fromJson(String source) =>
      Category.fromMap(json.decode(source) as Map<String, dynamic>);

  @override
  String toString() =>
      'Category(imageCategory: $imageCategory, textCategory: $textCategory)';

  @override
  bool operator ==(covariant Category other) {
    if (identical(this, other)) return true;

    return other.imageCategory == imageCategory &&
        other.textCategory == textCategory;
  }

  @override
  int get hashCode => imageCategory.hashCode ^ textCategory.hashCode;
}

final dummyCategory = [
  Category(
    imageCategory: "assets/icon_category_all",
    textCategory: categoryAll,
  ),
  Category(
    imageCategory: "assets/icon_category_americano ",
    textCategory: categoryAmericano,
  ),
  Category(
    imageCategory: "assets/icon_category_cappuccino ",
    textCategory: categoryCappuccino,
  ),
  Category(
    imageCategory: "assets/icon_category_espresso ",
    textCategory: categoryEspresso,
  ),
  Category(
    imageCategory: "assets/icon_category_frappe ",
    textCategory: categoryFrappe,
  ),
  Category(
    imageCategory: "assets/icon_category_latte ",
    textCategory: categoryLatte,
  ),
  Category(
    imageCategory: "assets/icon_category_macchiato ",
    textCategory: categoryMacchiato,
  ),
  Category(
    imageCategory: "assets/icon_category_mocha ",
    textCategory: categoryMocha,
  ),
];
