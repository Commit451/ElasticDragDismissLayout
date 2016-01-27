# ElasticDragDismissLayout
Standard ViewGroups which responds to nested scrolls to create drag-dismissable layouts. Original code taken from [Plaid](https://github.com/nickbutcher/plaid) and made backwards compatible.

[![Build Status](https://travis-ci.org/Commit451/ElasticDragDismissLayout.svg?branch=master)](https://travis-ci.org/Commit451/ElasticDragDismissLayout)

# Gradle Dependency
Easily reference the library in your Android projects using this dependency in your module's `build.gradle` file:

```Gradle
dependencies {
    compile 'com.commit451:elasticdragdismisslayout:1.0.0'
}
```

# Usage
See the sample project for a full example. Within XML:

```xml
<com.commit451.elasticdragdismisslayout.ElasticDragDismissLinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/draggable_frame"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    app:dragDismissDistance="112dp"
    app:dragDismissScale="0.95">

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="?attr/actionBarSize"
        android:background="#993F51B5" />

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</com.commit451.elasticdragdismisslayout.ElasticDragDismissLinearLayout>
```
In addition, you will probably want to have your new activity have a theme that allows for transparency. See `styles.xml` for an example.

# Supported ScrollViews
- ScrollView (if 21+ and android:nestedScrollingEnabled="true")
- RecyclerView
- NestedScrollView (from support v4 library)

# Currently Created Elastic Views
- ElasticDragDismissFrameLayout
- ElasticDragDismissLinearLayout
- ElasticDragDismissRelativeLayout

You can create your own by using the `ElasticDragDismissDelegate` and using one of the created views as a reference.

License
--------

    Copyright 2015 Google, Inc.
    Copyright 2016 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.