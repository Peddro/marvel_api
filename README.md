# Marvel Api usage example

This applications uses databinding with a MVVM design architecture with Dagger2 and Rx for network calls.
The libraries used in this project where for the base structure such as, for example, network calls, image loading, and the support libraries.

### Nice to Have
- Instead of just showing the description of events/stories/comics... of the character we could make a second API call in order to get that information.

### Current Bugs
- If you select a favorite when in the character detail activity it will not show in the list. This happens because the character is passed by intent which will make that both references are different (it creates a new Character object with a new reference). To fix this we could do a couple of things:
  - Use dagger to have the selected Character in memory and having all components using that Character
  - Create an observer that reflects the change of the Character in the view
  - Have a database with all the characters and fetch the character from there.
- Unit testings are making real API calls. This should not happened and we should prevent it by adding a MockWebServer

