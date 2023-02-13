import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      debugShowCheckedModeBanner: false,
      home: const Scaffold(
        body: MyForm(),
      ),
    );
  }
}

class FormInputState extends ChangeNotifier {
  String _input = "";

  String get input => _input;

  set input(String value) {
    _input = value;
    notifyListeners();
  }
}

class MyForm extends StatelessWidget {
  const MyForm({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => FormInputState(),
      child: const FormInput(),
    );
  }
}

class FormInput extends StatelessWidget {
  const FormInput({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    final state = context.read<FormInputState>();
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
        children: [
          TextFormField(
            keyboardType: TextInputType.number,
            decoration: const InputDecoration(
              hintText: "Nama",
              border: OutlineInputBorder(),
            ),
            onChanged: (value) {
              state.input = value;
            },
          ),
          const SizedBox(height: 8),
          Consumer<FormInputState>(
            builder: (context, value, child) {
              return Text(value.input);
            },
          ),
        ],
      ),
    );
  }
}
