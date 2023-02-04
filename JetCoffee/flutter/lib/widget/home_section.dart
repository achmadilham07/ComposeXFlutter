import 'package:flutter/material.dart';
import 'package:flutter/src/widgets/framework.dart';
import 'package:flutter/src/widgets/placeholder.dart';
import 'package:jet_coffee/widget/section_text.dart';

class HomeSection extends StatelessWidget {
  final String title;
  final Widget content;

  const HomeSection({
    super.key,
    required this.title,
    required this.content,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        SectionText(title: title),
        content,
      ],
    );
  }
}
