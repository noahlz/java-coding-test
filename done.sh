#/bin/sh

export ANT_HOME=./tools/ant

cmd="$ANT_HOME/bin/ant done"

echo "building solution using ant"
echo "running command: $cmd"

$cmd
