var app = angular.module('MyBoard');
app.factory('ItemService', function () {

  var makeNewItem = function (state) {

    var item = {
      title: '',
      text: '',
      state: 0
    };

    switch (state) {
      case 'backlog':
        item.state = 0;
        break;
      case 'todo':
        item.state = 1;
        break;
      case 'busy':
        item.state = 2;
      default:
        break;
    }

    return item;
  };
  return {
    makeNewItem: makeNewItem
  }
});
