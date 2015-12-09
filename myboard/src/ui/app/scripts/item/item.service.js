var app = angular.module('MyBoard');
app.factory('ItemService', function () {


  var constructNewItem = function()  {
    var item = {
      title: '',
      text: '',
      state: 0,
      drag: true
    };

    return item;
  };
  var makeNewItem = function (state) {
    var item = constructNewItem();
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
