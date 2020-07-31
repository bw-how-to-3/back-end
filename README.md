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
returns
```
{
    "access_token": "943ae615-a4ea-4926-877b-171d4d407c36",
    "token_type": "bearer",
    "scope": "read trust write"
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
returns
```

    {
        "postid": 4,
        "title": "TEST",
        "body": "TESTING, 123....., TESTING, 123",
        "upvotes": 0,
        "downvotes": 0,
        "votes": [],
        "user": {
            "userid": 3,
            "username": "admin",
            "roles": [
                {
                    "role": {
                        "roleid": 1,
                        "name": "ADMIN"
                    }
                }
            ],
            "votedPost": []
        }
    },
    {
        "postid": 6,
        "title": "title",
        "body": "post body ",
        "upvotes": 2,
        "downvotes": 2,
        "votes": [
            {
                "user": {
                    "userid": 5,
                    "username": "newUser",
                    "roles": [
                        {
                            "role": {
                                "roleid": 2,
                                "name": "USER"
                            }
                        }
                    ],
                    "votedPost": [
                        {
                            "post": {
                                "postid": 6,
                                "title": "title",
                                "body": "post body ",
                                "upvotes": 2,
                                "downvotes": 2
                            }
                        }
                    ]
                }
            }
        ],
        "user": {
            "userid": 5,
            "username": "newUser",
            "roles": [
                {
                    "role": {
                        "roleid": 2,
                        "name": "USER"
                    }
                }
            ],
            "votedPost": [
                {
                    "post": {
                        "postid": 6,
                        "title": "title",
                        "body": "post body ",
                        "upvotes": 2,
                        "downvotes": 2
                    }
                }
            ]
        }
    },
    {
        "postid": 9,
        "title": "Hey",
        "body": "this is my test",
        "upvotes": 0,
        "downvotes": 0,
        "votes": [],
        "user": {
            "userid": 8,
            "username": "joe",
            "roles": [
                {
                    "role": {
                        "roleid": 2,
                        "name": "USER"
                    }
                }
            ],
            "votedPost": []
        }
    },
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
returns
```
{
    "postid": 6,
    "title": "title",
    "body": "post body ",
    "upvotes": 0,
    "downvotes": 0,
    "votes": [],
    "user": {
        "userid": 5,
        "username": "newUser",
        "roles": [
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            }
        ],
        "votedPost": []
    }
}
```

## edit post - authorized users
```
PUT /posts/post/{postid}
```
returns
```
{
    "postid": 6,
    "title": "title",
    "body": "post body ",
    "upvotes": 0,
    "downvotes": 0,
    "votes": [],
    "user": {
        "userid": 5,
        "username": "newUser",
        "roles": [
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            }
        ],
        "votedPost": []
    }
}
```

## delete post - authorized users
```
DELETE /posts/post/{postid}
```

## get all post by current user - authorized users

```
GET /posts/user
```
returns
```
[
    {
        "postid": 4,
        "title": "TEST",
        "body": "TESTING, 123....., TESTING, 123",
        "upvotes": 0,
        "downvotes": 0,
        "votes": [],
        "user": {
            "userid": 3,
            "username": "admin",
            "roles": [
                {
                    "role": {
                        "roleid": 1,
                        "name": "ADMIN"
                    }
                }
            ],
            "votedPost": []
        }
    }
]
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
returns
```
{
    "postid": 6,
    "title": "title",
    "body": "post body ",
    "upvotes": 2,
    "downvotes": 2,
    "votes": [
        {
            "user": {
                "userid": 5,
                "username": "newUser",
                "roles": [
                    {
                        "role": {
                            "roleid": 2,
                            "name": "USER"
                        }
                    }
                ],
                "votedPost": [
                    {
                        "post": {
                            "postid": 6,
                            "title": "title",
                            "body": "post body ",
                            "upvotes": 2,
                            "downvotes": 2
                        }
                    }
                ]
            }
        }
    ],
    "user": {
        "userid": 5,
        "username": "newUser",
        "roles": [
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            }
        ],
        "votedPost": [
            {
                "post": {
                    "postid": 6,
                    "title": "title",
                    "body": "post body ",
                    "upvotes": 2,
                    "downvotes": 2
                }
            }
        ]
    }
}
```
## get all users - admin only
```
GET /users/users
```
returns
```
[
    {
        "userid": 3,
        "username": "admin",
        "roles": [
            {
                "role": {
                    "roleid": 1,
                    "name": "ADMIN"
                }
            }
        ],
        "posts": [
            {
                "postid": 4,
                "title": "TEST",
                "body": "TESTING, 123....., TESTING, 123",
                "upvotes": 0,
                "downvotes": 0,
                "votes": []
            }
        ],
        "votedPost": []
    },
    {
        "userid": 5,
        "username": "newUser",
        "roles": [
            {
                "role": {
                    "roleid": 2,
                    "name": "USER"
                }
            }
        ],
        "posts": [
            {
                "postid": 6,
                "title": "title",
                "body": "post body ",
                "upvotes": 2,
                "downvotes": 2,
                "votes": [
                    {
                        "user": {
                            "userid": 5,
                            "username": "newUser",
                            "roles": [
                                {
                                    "role": {
                                        "roleid": 2,
                                        "name": "USER"
                                    }
                                }
                            ],
                            "votedPost": [
                                {
                                    "post": {
                                        "postid": 6,
                                        "title": "title",
                                        "body": "post body ",
                                        "upvotes": 2,
                                        "downvotes": 2
                                    }
                                }
                            ]
                        }
                    }
                ]
            }
        ],
        "votedPost": [
            {
                "post": {
                    "postid": 6,
                    "title": "title",
                    "body": "post body ",
                    "upvotes": 2,
                    "downvotes": 2
                }
            }
        ]
    },
```

## get user by username - admin only
```
GET /users/user/username/{username}
```

## get list of users with username like - admin only
```
GET /users/user/username/like/{username}
```

## add admin role to current user - authorized
```
PUT /roles/user{userid}
```
## delete user by id
```
DELETE /users/user/{userid}
```

