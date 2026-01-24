#/bin/bash
cool_stuffs=($'\U01F920' $'\U01F4AF' $'\U01F496' $'\U01F44B' $'\U01F4A3' $'\U01F389' $'\U01F9E8' $'\U2728' $'\U01F38A')


git fetch -p
if [ $? -ne 0 ]
then
    printf "\n\U01F92E what? git fetch failed!  are you even in a git repo? cause it don't seem like `pwd` is.\n\n"
    exit 1
fi

git checkout dev
if [ $? -ne 0 ]
then
    printf "\n\U01F92E oh my golly geepers gracious! I couldn't checkout dev.  I'm just stoppin now.\n\n"
    exit 1
fi

for branch in `git branch -vv | grep ': gone]' | awk '{print  $1}'`
do
    git branch -D $branch 
    echo -e "${cool_stuffs[$RANDOM % ${#cool_stuffs[@]}]} just killed off \e[30;46m$branch\e[0m"
    
done
git reset --hard origin/dev

printf "\n\U01F4AA\U01F49E\U01F525\U01F332\U01F308\U01F499\U01F49A\U01F49B\U01F49C yayayayay your branches are super now.\n\n"
