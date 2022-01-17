**TweetLite** is an android app that allows a user to view his Twitter timeline and post a new tweet. The app utilizes [Twitter REST API](https://dev.twitter.com/rest/public).

Time spent: **6** hours spent in total

## User Stories

The following **required** functionality is completed:

  - [X] User can **compose and post a new tweet**
  - [X] User can click a “Compose” icon in the Action Bar on the top right
  - [X] User can then enter a new tweet and post this to twitter
  - [X] User is taken back to home timeline with **new tweet visible** in timeline
  - [X] Newly created tweet should be manually inserted into the timeline and not rely on a full refresh
  - [X] User can **see a counter with total number of characters left for tweet** on compose tweet page

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://i.imgur.com/0qviHXE.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Takes a bit of time for the character count in compose activity to load current length of tweet.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Android

## License

    Copyright [2019] [Bisdeep - Deep Biswas]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
