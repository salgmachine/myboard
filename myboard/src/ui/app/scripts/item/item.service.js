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
    if(state >= 10 && state <= 50) {
      item.state = state;
    }
    return item;
  };
  return {
    makeNewItem: makeNewItem
  }
});
