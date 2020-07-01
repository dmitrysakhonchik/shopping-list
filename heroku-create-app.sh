#!/usr/bin/env sh

heroku apps:create diem-shopping-list
heroku addons:create heroku-postgresql:hobby-dev --app diem-shopping-list