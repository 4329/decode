#/bin/bash
cool_stuffs=('🤠' '💯' '💖' '👋' '💣' '🎉' '🧨' '✨' '🎊' '🍄')


git fetch -p
if [ $? -ne 0 ]
then
    printf "\n😨 what? git fetch failed!  are you even in a git repo? cause it don't seem like `pwd` is.\n\n"
    exit 1
fi

git checkout dev
if [ $? -ne 0 ]
then
    printf "\n😱 oh my golly geepers gracious! I couldn't checkout dev.  I'm just stoppin now.\n\n"
    exit 1
fi

for branch in `git branch -vv | grep ': gone]' | awk '{print  $1}'`
do
    git branch -D $branch 
    echo -e "${cool_stuffs[$RANDOM % ${#cool_stuffs[@]}]} just killed off \e[30;46m$branch\e[0m"
    
done
git reset --hard origin/dev

printf "\n🔥 🦾 💞 🌲 🌈 🩷 🧡 💛 💚 💙 💜 yayayayay your branches are super now.\n\n"
