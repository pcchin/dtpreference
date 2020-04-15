# dtpreference
[![Bintray](https://api.bintray.com/packages/pcchin/dtpreference/com.pcchin.dtpreference/images/download.svg)](https://bintray.com/pcchin/dtpreference/com.pcchin.dtpreference/_latestVersion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.pcchin.dtpreference/dtpreference/badge.svg)](https://search.maven.org/artifact/com.pcchin.dtpreference/dtpreference)

## Library Info
This library contains a date preference, time preference and date and time preference library for Android, 
allowing users to choose a specific date for DatePreference, a specific time for TimePreference, and a specific date and time for DateTimePreference.

The time value for DatePreference would be at midnight of the selected date, 
while the date value for the TimePreference would be the current date.

All the values for the preference are stored in the form of a `long` variable representing the number of milliseconds since the epoch, 
which can be easily converted to a Java `Date` object through its constructor `Date (long date)`.

## Installation
This library is available in JCenter and Maven Central. To install, you would need to include the following into your `project/build.gradle`:

```
implementation 'com.pcchin.dtpreference:dtpreference:1.0.0'
```

## Usage

For the layout XML, simply include the preference without any additional changes. e.g.:
```XML
<com.pcchin.dtpreference.DatePreference
    android:title="Date Preference"
    android:key="pref_date"
/>
```

## Example Implementation

## Contribution
Any contribution is welcome, feel free to add any issues or pull requests to the repository.

## License
This library is licensed under the [Apache 2.0 License](/LICENSE).