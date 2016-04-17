var app = angular.module('MyBoard');
app.factory('Boards', function($resource){
  return $resource("boards");
});
