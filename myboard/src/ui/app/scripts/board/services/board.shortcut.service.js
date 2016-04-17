var app = angular.module('MyBoard');
app.factory('ItemDialogShortcuts', function (taOptions, hotkeys, $rootScope, ItemService, ItemDialogService) {

  var setupEditorShortcuts = function (scope) {
    taOptions.keyMappings.push({
      // register escape key as to closing the dialog for a quick-out functionality
      commandKeyCode: 'esc', testForKey: function (event) {
        // listen for the escape keycode..
        if (event.keyCode === 27) {
          // and close the dialog
          scope.dialog.hideDialog();
          return true;
        }
      }
    }, {
      // disable tab key shortcut as we want to move the cursor to the next input field instead of inserting a blockquote
      commandKeyCode: 'TabKey', testForKey: function (event) {
        return false;
      }
    });
  };

  var setupDialogShortcuts = function (scope) {
    hotkeys.bindTo(scope)
      .add({
        combo: 'esc',
        description: 'Closes the item overlay',
        allowIn: ['INPUT', 'SELECT', 'TEXTAREA'],
        callback: function () {
          ItemDialogService.toggle(false);
        }
      });
  };

  var setup = function (scope) {
    setupEditorShortcuts(scope);
    setupDialogShortcuts(scope);
  };

  return {
    setup: setup
  }

});
