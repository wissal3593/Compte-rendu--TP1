# Travail à faire (Compte Rendu)

## Créer et exécuter une application

* Cette application Android permet de gérer une liste de mots stockés dans une base de données SQLite locale.
* L'utilisateur peut ajouter, modifier et supprimer des mots via une interface RecyclerView.

## Répondre à ces questions

### **Question 1**

**Q1.** Quelle API moderne remplace la méthode dépréciée `startActivityForResult()` pour lancer une activité et récupérer un résultat ?

📋 **A1.** Choisissez-en un:

* [x] **(a)** `ActivityResultLauncher` avec `registerForActivityResult()`
* [ ] **(b)** `IntentResultManager` avec `launchActivity()`
* [ ] **(c)** `ActivityManager` avec `startForResult()`
* [ ] **(d)** `ResultCallback` avec `executeActivity()`

### **Question 2**

**Q2.** Dans le pattern ViewHolder du RecyclerView, quel est le rôle principal de la classe `WordViewHolder` ?

📋 **A2.** Choisissez-en un:

* [ ] **(a)** Gérer la base de données SQLite
* [x] **(b)** Contenir les références aux vues d'un élément de la liste pour éviter les appels répétés à `findViewById()`
* [ ] **(c)** Créer de nouvelles instances d'activités
* [ ] **(d)** Gérer les animations de transition entre activités

### **Question 3**

**Q3.** Quelle méthode de `RecyclerView.Adapter` est appelée pour déterminer le nombre d'éléments à afficher dans la liste ?

📋 **A3.** Choisissez-en un:

* [ ] **(a)** `onCreateViewHolder()`
* [ ] **(b)** `onBindViewHolder()`
* [x] **(c)** `getItemCount()`
* [ ] **(d)** `getViewType()`

### **Question 4**

**Q4.** Dans `EditWordActivity`, comment les données sont-elles retournées à l'activité appelante (`MainActivity`) ?

```java
public void returnReply(View view) {
    String word = ((EditText) findViewById(R.id.edit_word)).getText().toString();
    Intent replyIntent = new Intent();
    replyIntent.putExtra(EXTRA_REPLY, word);
    replyIntent.putExtra(WordListAdapter.EXTRA_ID, mId);
    setResult(RESULT_OK, replyIntent);
    finish();
}
```

📋 **A4.** Choisissez-en un:

* [ ] **(a)** Via un `Bundle` passé dans le constructeur de `MainActivity`
* [x] **(b)** Via un `Intent` contenant des extras, avec `setResult()` avant d'appeler `finish()`
* [ ] **(c)** Via une variable statique partagée entre les deux activités
* [ ] **(d)** Via une base de données SQLite temporaire
* [ ] **(e)** Via un fichier SharedPreferences
* [ ] **(f)** Via un callback interface implémenté par `MainActivity`

### **Question 5**

**Q5.** Dans Android, quelle(s) méthode(s) de la classe `SQLiteOpenHelper` DOIT/DOIVENT être implémentée(s) obligatoirement pour gérer une base de données SQLite ?

📋 **A5.** Choisissez-en un:

* [ ] **(a)** Uniquement `onCreate()`
* [ ] **(b)** Uniquement `onUpgrade()`
* [x] **(c)** `onCreate()` et `onUpgrade()`
* [ ] **(d)** `onCreate()`, `onUpgrade()` et `onDowngrade()`
* [ ] **(e)** `onCreate()`, `onOpen()` et `onConfigure()`

### **Question 6**

**Q6.** Dans la méthode `onBindViewHolder()` du `RecyclerView.Adapter`, quel est le paramètre `position` ?

📋 **A6.** Choisissez-en un:

* [ ] **(a)** L'identifiant unique de la vue dans le RecyclerView
* [x] **(b)** L'index de l'élément dans l'ensemble de données (dataset) à afficher
* [ ] **(c)** La position physique en pixels de l'élément sur l'écran
* [ ] **(d)** Le nombre total d'éléments dans la liste

### **Question 7**

**Q7.** Quelle méthode du cycle de vie d'une Activity est appelée lorsque l'activité devient visible pour l'utilisateur ?

📋 **A7.** Choisissez-en un:

* [ ] **(a)** `onCreate()`
* [x] **(b)** `onStart()`
* [ ] **(c)** `onResume()`
* [ ] **(d)** `onRestart()`

### **Question 8**

**Q8.** Comment récupérer une valeur String passée via un Intent avec la clé `"EXTRA_WORD"` dans l'activité de destination ?

📋 **A8.** Choisissez-en un:

* [ ] **(a)** `getIntent().getString("EXTRA_WORD")`
* [ ] **(b)** `getIntent().getStringExtra("EXTRA_WORD")`
* [ ] **(c)** `getIntent().getExtras().getString("EXTRA_WORD")`
* [x] **(d)** Les réponses **(b)** et **(c)** sont correctes

### **Question 9**

**Q9.** Quel `LayoutManager` est utilisé pour afficher les éléments d'un RecyclerView en une seule colonne verticale ?

📋 **A9.** Choisissez-en un:

* [ ] **(a)** `GridLayoutManager`
* [x] **(b)** `LinearLayoutManager` avec orientation `VERTICAL`
* [ ] **(c)** `StaggeredGridLayoutManager`
* [ ] **(d)** `FlexboxLayoutManager`

### **Question 10**

**Q10.** Quelle méthode SQL est utilisée pour insérer une nouvelle ligne dans une table SQLite via l'API Android ?

📋 **A10.** Choisissez-en un:

* [x] **(a)** `SQLiteDatabase.insert()`
* [ ] **(b)** `SQLiteDatabase.add()`
* [ ] **(c)** `SQLiteDatabase.create()`
* [ ] **(d)** `SQLiteDatabase.put()`

## Dépannage

### workflow (CI)

Si le workflow GitHub Actions échoue, suivez ces étapes localement avant de re-pousser:

1) Vérifiez la structure du projet

   * ~~Placez votre application dans le dossier [`application`](/application/) comme demandé.~~
   * Vérifiez que le fichier `README.md` est à la racine du projet.

2) Formatage du code (Spotless)

    > [!TIP]
    > Sous Windows, il est recommandé d'utiliser Git Bash pour exécuter ces commandes.  
    > Sur macOS ou Linux, utilisez le terminal intégré ou votre shell préféré.

   * Vérifiez le formatage localement (utilisez le fichier init-script présent dans `.github`):

        > [!WARNING]
        > Assurez-vous d'exécuter ces commandes dans le répertoire `application` afin que Gradle utilise les bons chemins relatifs et le contexte du projet.

        ```bash
        ./gradlew --init-script .github/spotless.init.gradle spotlessCheck
        ```

        > [!CAUTION]
        > Si vous avez des problèmes avec l'exécution de `gradlew`, essayez d'accorder les permissions d'exécution:
        >
        > ```bash
        > chmod +x gradlew
        > ```

   * Corrigez automatiquement le formatage:

        ```bash
        ./gradlew --init-script .github/spotless.init.gradle spotlessApply
        ```

   * Validez vos changements:

        ```bash
        # * branche `dev`
        git add -A
        git commit -m "style: apply Spotless formatting"
        ```

3) Relancez le `workflow`

   * Poussez vos corrections sur la branche `dev` ou relancez manuellement le `workflow` dans l’onglet “Actions”.

        ```bash
        # * branche `dev`
        git push
        ```

> [!IMPORTANT]
> Si vous rencontrez des problèmes, n'hésitez pas à demander de l'aide en ouvrant une [issue](../../issues/new/choose) sur le dépôt GitHub.
