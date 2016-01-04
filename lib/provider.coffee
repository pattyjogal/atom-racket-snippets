fs = require 'fs'
path = require 'path'
console.log "Workin!"
module.exports =
  selector: '.source.racket'
  filterSuggestion: true
  getSuggestions: (req) ->
    console.log  req
    {prefix} = req
    if req.prefix.substring(prefix.length) is "" or  req.prefix.substring(prefix.length) is "("
      @getRacketFunctions()
  onDidInsertSuggestion: ({editor, suggestion}) ->
    console.log "Suggestion"
    setTimeout(@triggerAutocomplete.bind(this, editor), 1)
    atom.commands.dispatch(atom.views.getView(editor), 'snippets:expand')
  triggerAutocomplete: (editor)  ->
    atom.commands.dispatch(atom.views.getView(editor), 'autocomplete-plus:activate', activatedManually: false)
  loadCompletions: ->
    @completions = {}
    fs.readFile path.resolve(__dirname, '..', 'completions.json'), (error, content) =>
      console.log JSON.parse(content)
      @completions = JSON.parse(content) unless error?
      return
  getFunctions: (f) ->
    f = @completions.functions[f]
  getRacketFunctions: (prefix) ->
    c = []
    console.log @completions.functions
    for f of @completions.functions
      c.push(@buildRacketFunctionCompletion(f))
    c
  buildRacketFunctionCompletion: (f) ->
    console.log "fns built"
    text: f
    type: 'function'
    description: "Racket #{f} function"
#Remember, I found the master thread of racket stuff in blueboxes.rktd
