var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
    	templateUrl : 'S_common/body.html',
    })
    .when('/login',{
templateUrl : 'S_user/login.html',
})
    .when("/register", {
        templateUrl : 'S_user/registration.html'
    })
    .when("/blue", {
        templateUrl : "blue.htm"
    });
});