import 'package:flutter/material.dart';
import 'package:my_nav_drawer/utils/string.dart';

import 'model/menu_item.dart';

void main() {
  runApp(const MyNavDrawerApp());
}

final scaffoldMessengerKey = GlobalKey<ScaffoldMessengerState>();

class MyNavDrawerApp extends StatelessWidget {
  const MyNavDrawerApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      scaffoldMessengerKey: scaffoldMessengerKey,
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
  final scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: scaffoldKey,
      appBar: MyTopBar(
        onMenuClick: () {
          scaffoldKey.currentState?.openDrawer();
        },
      ),
      drawer: Drawer(
        child: MyDrawerContent(
          onItemSelected: (title) {
            scaffoldKey.currentState?.closeDrawer();
            scaffoldMessengerKey.currentState?.showSnackBar(
              SnackBar(
                content: Text(comingSoon(title)),
                action: SnackBarAction(
                  label: subscribeQuestion,
                  onPressed: () {},
                ),
              ),
            );
          },
        ),
      ),
      drawerEnableOpenDragGesture:
          scaffoldKey.currentState?.isDrawerOpen ?? false,
      body: const Center(child: Text(helloWorld)),
    );
  }
}

class MyTopBar extends StatelessWidget implements PreferredSizeWidget {
  final Function() onMenuClick;

  const MyTopBar({
    super.key,
    required this.onMenuClick,
  });

  @override
  Widget build(BuildContext context) {
    return AppBar(
      leading: IconButton(
        onPressed: () {
          onMenuClick();
        },
        icon: const Icon(Icons.menu),
        tooltip: menu,
      ),
      title: const Text(appName),
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
}

class MyDrawerContent extends StatelessWidget {
  final Function(String title) onItemSelected;
  const MyDrawerContent({
    super.key,
    required this.onItemSelected,
  });

  @override
  Widget build(BuildContext context) {
    final items = [
      MenuItem(
        title: home,
        icon: const Icon(Icons.home),
      ),
      MenuItem(
        title: favourite,
        icon: const Icon(Icons.favorite),
      ),
      MenuItem(
        title: profile,
        icon: const Icon(Icons.account_circle),
      ),
    ];
    return Column(
      mainAxisSize: MainAxisSize.max,
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: [
        Container(
          height: 190,
          color: Theme.of(context).colorScheme.primary,
        ),
        for (var item in items)
          ListTile(
            title: Text(item.title),
            leading: item.icon,
            onTap: () {
              onItemSelected(item.title);
            },
          ),
      ],
    );
  }
}

/**

@Composable
fun MyDrawerContent(
    modifier: Modifier = Modifier,
    onItemSelected: (title: String) -> Unit,
) {
    Column(modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(190.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
        )
        for (item in items) {
            Row(
                modifier = Modifier
                    .clickable { onItemSelected(item.title) }
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(32.dp))
                Text(text = item.title, style = MaterialTheme.typography.subtitle2)
            }
        }
        Divider()
    }
} 
 */