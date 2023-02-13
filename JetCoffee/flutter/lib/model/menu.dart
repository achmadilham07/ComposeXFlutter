import 'dart:convert';

import '../utils/list_extension.dart';

class Menu {
  final String image;
  final String title;
  final String price;
  Menu({
    required this.image,
    required this.title,
    required this.price,
  });

  Menu copyWith({
    String? image,
    String? title,
    String? price,
  }) {
    return Menu(
      image: image ?? this.image,
      title: title ?? this.title,
      price: price ?? this.price,
    );
  }

  Map<String, dynamic> toMap() {
    return <String, dynamic>{
      'image': image,
      'title': title,
      'price': price,
    };
  }

  factory Menu.fromMap(Map<String, dynamic> map) {
    return Menu(
      image: map['image'] as String,
      title: map['title'] as String,
      price: map['price'] as String,
    );
  }

  String toJson() => json.encode(toMap());

  factory Menu.fromJson(String source) =>
      Menu.fromMap(json.decode(source) as Map<String, dynamic>);

  @override
  String toString() => 'Menu(image: $image, title: $title, price: $price)';

  @override
  bool operator ==(covariant Menu other) {
    if (identical(this, other)) return true;

    return other.image == image && other.title == title && other.price == price;
  }

  @override
  int get hashCode => image.hashCode ^ title.hashCode ^ price.hashCode;
}

final dummyMenu = [
  Menu(
    image: "assets/menu1.webp",
    title: "Tiramisu Coffee Milk",
    price: "Rp 28.000",
  ),
  Menu(
    image: "assets/menu2.webp",
    title: "Pumpkin Spice Latte",
    price: "Rp 18.000",
  ),
  Menu(
    image: "assets/menu3.webp",
    title: "Light Cappuccino",
    price: "Rp 20.000",
  ),
  Menu(
    image: "assets/menu4.webp",
    title: "Choco Creamy Latte",
    price: "Rp 16.000",
  ),
];

final dummyBestSellerMenu = dummyMenu.shuffled();
