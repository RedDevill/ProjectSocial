
var app = angular.module('myApp2', ['ngRoute']);

app.config(function($routeProvider) {
  $routeProvider

  .when('/', {
    templateUrl : 'Ex2/view/body.html',
    controller : 'HomeController'
   
  })

  .when('/blog', {
    templateUrl : 'Ex2/view/blog.html',
    controller : 'BlogController'
   
  })

  .when('/friend', {
    templateUrl : 'Ex2/view/friend.html',
    controller : 'FriendController'
   
  })
  
   .when('/job', {
    templateUrl : 'Ex2/view/job.html',
    controller : 'JobController'
   
  })

  .otherwise({redirectTo: '/'});
});

