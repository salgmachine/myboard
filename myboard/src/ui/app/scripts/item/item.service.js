var app = angular.module('MyBoard');
app.factory('Items', function($resource){

  return $resource("/api/items/:id");

});
