# ElasticDragDismissLayout
Standard ViewGroups which responds to nested scrolls to create drag-dismissable layouts. Original code taken from [Plaid](https://github.com/nickbutcher/plaid) and made backwards compatible.

[![Build Status](https://travis-ci.org/Commit451/ElasticDragDismissLayout.svg?branch=master)](https://travis-ci.org/Commit451/ElasticDragDismissLayout) [![](https://jitpack.io/v/Commit451/ElasticDragDismissLayout.svg)](https://jitpack.io/#Commit451/ElasticDragDismissLayout)

<img src="/art/sample.gif?raw=true" width="200px">

# Gradle Dependency

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Then, add the library to your project `build.gradle`
```gradle
dependencies {
    implementation 'com.github.Commit451:ElasticDragDismissLayout:1.0.4'
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

To allow for dismiss:
```java
mDraggableFrame.addListener(new ElasticDragDismissListener() {
        @Override
        public void onDrag(float elasticOffset, float elasticOffsetPixels, float rawOffset, float rawOffsetPixels) {}

        @Override
        public void onDragDismissed() {
            //if you are targeting 21+ you might want to finish after transition
            finish();
        }
    });
```

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
    Copyright 2017 Commit 451

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
