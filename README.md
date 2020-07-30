# How-To back-end

# [Full documentation] (https://heftyb-how-to.herokuapp.com/swagger-ui.html#/)


## Create new User - returns token
```
/createnewuser
```
expecting
```
{
  "password": "string",
  "username": "string"
}
```
## login - returns token
```
/login or /oauth/token
``` 
expects a valid username password

## logout - removes token from store - authorized users
```
/logout , or /oauth/revoke-token 
```

## get all post - authorized users
```
GET /posts/posts
```

## submit post - authorized users
```
POST /posts/post
```
requires
```
"title": "String",
"body": "String"
```

## edit post - authorized users
```
PUT /posts/post/{postid}
```

## delete post - authorized users
```
DELETE /posts/post/{postid}
```

## get all post by current user - authorized users

```
GET /posts/user
```

## upvote / downvote post - authorized users
```
PUT /posts/post/vote/{postid}
```
requires boolean
```
false - will upvote
true - will downvote
```

## get all users - admin only
```
GET /users/users
```

## get user by username - admin only
```
GET /users/user/username/{username}
```

## get list of users with username like - admin only
```
GET /users/user/username/like/{username}
```
