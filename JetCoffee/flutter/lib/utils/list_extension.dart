import 'dart:math';

extension ListExtension<T> on List<T> {

  List<T> shuffled([int start = 0, int? end, Random? random]) {
    final list = map((e) => e).toList();
    list.shuffle();
    return list;
  }
}
